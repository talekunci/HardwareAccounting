package com.accounting.HardwareAccounting.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import org.springframework.util.StringUtils;

import jakarta.persistence.PersistenceContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.UnexpectedTypeException;
import java.lang.reflect.Field;

public class UniqueValidation implements ConstraintValidator<IsUnique, Object> {

    private String table;

    private String field;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(IsUnique constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.table = constraintAnnotation.table();

        if (!StringUtils.hasText(table)) {
            Class<?> model = constraintAnnotation.model();
            Table tableAnnotation = model.getDeclaredAnnotation(Table.class);
            if (tableAnnotation != null) {
                this.table = tableAnnotation.name();
            } else {
                throw new UnexpectedTypeException("Model or table name not declared.");
            }
        }
    }

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        Object fieldValue = null;

        try {
            Field field = dto.getClass().getDeclaredField(this.field);
            field.setAccessible(true);
            fieldValue = field.get(dto);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (fieldValue != null) {
            Query query = entityManager.createQuery(
                    String.format("select count(*) > 0 from %s where %s = :param", this.table, this.field)
            );
            query.setParameter("param", fieldValue);

            Boolean presentInDb = (Boolean) query.getSingleResult();

            if (presentInDb) {
                context.buildConstraintViolationWithTemplate("This field isn't unique.")
                        .addPropertyNode(this.field)
                        .addConstraintViolation();
            }

            return !presentInDb;
        }

        return true;
    }
}
