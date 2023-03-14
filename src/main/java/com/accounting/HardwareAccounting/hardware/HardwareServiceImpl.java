package com.accounting.HardwareAccounting.hardware;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository repository;
    private final MaintenanceDateRepository maintenanceDateRepository;
    private final ModelMapper mapper;

    @Autowired
    public HardwareServiceImpl(HardwareRepository repository, MaintenanceDateRepository maintenanceDateRepository, ModelMapper mapper) {
        this.repository = repository;
        this.maintenanceDateRepository = maintenanceDateRepository;
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

                    if (StringUtils.hasText(dto.getSerialNumber()))
                        h.setSerialNumber(dto.getSerialNumber());

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

                    return h;
                })
                .ifPresent(repository::save);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public SortedSet<MaintenanceDateDto> getMaintenanceDatesByHardwareUuid(UUID uuid) {
        Optional<Hardware> hardware = repository.findById(uuid);
        if (hardware.isPresent()) {
            return hardware.get()
                    .getMaintenanceDates()
                    .stream().map(this::mapToDtoMaintenanceDate)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(MaintenanceDateDto::getDate).reversed())
                    ));
        }

        return Collections.emptySortedSet();
    }

    @Override
    public void addMaintenanceDateByUuid(UUID hardwareUuid, MaintenanceDateDto dateDto) {
        repository.findById(hardwareUuid)
                .ifPresent(hardware -> {
                    MaintenanceDate date = mapFromDtoMaintenanceDate(dateDto);
                    date.setHardware(hardware);
                    maintenanceDateRepository.save(date);
                });
    }

    @Override
    public void updateMaintenanceDate(UUID uuid, MaintenanceDateDto dateDto) {
        maintenanceDateRepository.findById(uuid)
                .map(date -> {
                    if (dateDto.getDate() != null)
                        date.setDate(dateDto.getDate());
                    if (StringUtils.hasText(dateDto.getDescription()))
                        date.setDescription(dateDto.getDescription());

                    return date;
                }).ifPresent(maintenanceDateRepository::save);
    }

    public Optional<MaintenanceDateDto> getMaintenanceDateByUuid(UUID uuid) {
        return maintenanceDateRepository.findById(uuid)
                .map(this::mapToDtoMaintenanceDate);
    }

    public void deleteMaintenanceDateByUuid(UUID hardwareUuid, UUID dateUuid) {
        maintenanceDateRepository.findById(dateUuid)
                .ifPresent(maintenanceDate -> {
                    repository.findById(hardwareUuid)
                            .ifPresent(h -> h.getMaintenanceDates().remove(maintenanceDate));

                    maintenanceDateRepository.deleteById(dateUuid);
                });
    }

    @Override
    public HardwareDto mapToDto(Hardware hardware) {
        return mapper.map(hardware, HardwareDto.class);
    }

    @Override
    public Hardware mapFromDto(HardwareDto dto) {
        return mapper.map(dto, Hardware.class);
    }

    @Override
    public MaintenanceDateDto mapToDtoMaintenanceDate(MaintenanceDate date) {
        return mapper.map(date, MaintenanceDateDto.class);
    }

    @Override
    public MaintenanceDate mapFromDtoMaintenanceDate(MaintenanceDateDto dto) {
        return mapper.map(dto, MaintenanceDate.class);
    }
}
