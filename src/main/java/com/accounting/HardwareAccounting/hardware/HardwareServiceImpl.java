package com.accounting.HardwareAccounting.hardware;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public HardwareServiceImpl(HardwareRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<HardwareDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDto> getByUuid(UUID uuid) {
        return repository
                .findById(uuid)
                .map(this::mapToDto);
    }

    @Override
    public Optional<HardwareDto> getBySerialNumber(String serialNumber) {
        return repository
                .findBySerialNumber(serialNumber)
                .map(this::mapToDto);
    }

    @Override
    public void create(HardwareDto dto) {
        repository.save(mapFromDto(dto));
    }

    //    Serial -number- and -manufacturingDate- CANNOT be changed
    @Override
    public void update(UUID uuid, HardwareDto dto) {
        repository.findById(uuid)
                .map(h -> {
                    if (StringUtils.hasText(dto.getManufacturer()))
                        h.setManufacturer(dto.getManufacturer());

                    if (StringUtils.hasText(dto.getName()))
                        h.setName(dto.getName());

                    if (StringUtils.hasText(dto.getDescription()))
                        h.setDescription(dto.getDescription());

                    if (dto.getInstallationDate() != null
                            && dto.getInstallationDate().getTime() > dto.getManufacturingDate().getTime())
                        h.setManufacturer(dto.getManufacturer());

                    if (StringUtils.hasText(dto.getInstallationAddress()))
                        h.setInstallationAddress(dto.getInstallationAddress());

                    if (dto.getOwnerPhoneNumber().length() <= 15)
                        h.setOwnerPhoneNumber(dto.getOwnerPhoneNumber());

                    if (StringUtils.hasText(dto.getOwnerEmail()))
                        h.setOwnerEmail(dto.getOwnerEmail());

                    if (dto.getMaintenanceDates() != null)
                        h.getMaintenanceDates().addAll(dto.getMaintenanceDates());


                    return h;
                })
                .ifPresent(repository::save);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public HardwareDto mapToDto(Hardware hardware) {
        return mapper.map(hardware, HardwareDto.class);
    }

    @Override
    public Hardware mapFromDto(HardwareDto dto) {
        return mapper.map(dto, Hardware.class);
    }
}
