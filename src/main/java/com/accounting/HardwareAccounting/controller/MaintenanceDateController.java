package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.configuration.OnlyAdminAllowed;
import com.accounting.HardwareAccounting.hardware.HardwareDto;
import com.accounting.HardwareAccounting.hardware.HardwareServiceImpl;
import com.accounting.HardwareAccounting.hardware.MaintenanceDateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/hardware/maintenanceDates")
public class MaintenanceDateController {

    private final HardwareServiceImpl service;

    public MaintenanceDateController(HardwareServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String getMaintenanceDates(@RequestParam UUID hardwareUuid, Model model) {
        Optional<HardwareDto> hardwareDto = service.getByUuid(hardwareUuid);

        if (hardwareDto.isPresent()) {
            model.addAttribute("datesSet", service.getMaintenanceDatesByHardwareUuid(hardwareUuid));
            model.addAttribute("hardware", hardwareDto.get());
        } else {
            System.out.printf("Hardware by uuid='%s' not found.%n", hardwareUuid);
            return "redirect:/hardware";
        }

        return "maintenance_dates";
    }

    @OnlyAdminAllowed
    @GetMapping("/new")
    public String showCreatingForm(@RequestParam UUID hardwareUuid, Model model) {
        Optional<HardwareDto> hardwareDto = service.getByUuid(hardwareUuid);

        if (hardwareDto.isPresent()) {
            var date = new MaintenanceDateDto();
            date.setHardware(hardwareDto.get());
            model.addAttribute("date", date);
        } else {
            return getMaintenanceDates(hardwareUuid, model);
        }

        return "maintenance_date_form";
    }

    @OnlyAdminAllowed
    @GetMapping("/edit")
    public String showEditingForm(@RequestParam UUID uuid, Model model) {
        Optional<MaintenanceDateDto> maintenanceDateDto = service.getMaintenanceDateByUuid(uuid);
        if (maintenanceDateDto.isPresent()) {
            model.addAttribute("date", maintenanceDateDto.get());
        } else {
            return getMaintenanceDates(uuid, model);
        }

        return "maintenance_date_form";
    }

    @OnlyAdminAllowed
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createMaintenanceDate(
            @RequestParam UUID hardwareUuid,
            @Valid @RequestBody MaintenanceDateDto maintenanceDateDto
    ) {
        service.addMaintenanceDateByUuid(hardwareUuid, maintenanceDateDto);
    }

    @OnlyAdminAllowed
    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMaintenanceDate(@Valid @RequestBody MaintenanceDateDto maintenanceDateDto) {
        service.updateMaintenanceDate(maintenanceDateDto);
    }

    @OnlyAdminAllowed
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMaintenanceDate(@RequestParam UUID uuid) {
        service.deleteMaintenanceDateByUuid(uuid);
    }
}
