package com.accounting.HardwareAccounting.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class UniqueValidation implements ConstraintValidator<IsUnique, Object> {

    private String entityType;

    private String field;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(IsUnique constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.entityType = constraintAnnotation.model().getSimpleName();

        if (!StringUtils.hasText(entityType)) {
            throw new UnexpectedTypeException("Model type is not declared.");
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
                    String.format("select count(*) > 0 from %s where %s = :param", this.entityType, this.field)
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
