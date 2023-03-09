package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.configuration.OnlyAdminAllowed;
import com.accounting.HardwareAccounting.hardware.HardwareDto;
import com.accounting.HardwareAccounting.hardware.HardwareServiceImpl;
import com.accounting.HardwareAccounting.hardware.MaintenanceDateDto;
import com.electronwill.nightconfig.core.conversion.Path;
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

    @GetMapping("/{hardwareUuid}")
    public String getMaintenanceDates(@PathVariable UUID hardwareUuid, Model model) {
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
    @GetMapping("/{hardwareUuid}/add")
    public String showCreatingForm(@PathVariable UUID hardwareUuid, Model model) {
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
    @GetMapping("/{dateUuid}/edit")
    public String showEditingForm(@PathVariable UUID dateUuid, Model model) {
        Optional<MaintenanceDateDto> maintenanceDateDto = service.getMaintenanceDateByUuid(dateUuid);
        if (maintenanceDateDto.isPresent()) {
            model.addAttribute("date", maintenanceDateDto.get());
        } else {
            return getMaintenanceDates(dateUuid, model);
        }

        return "maintenance_date_form";
    }

    @OnlyAdminAllowed
    @PostMapping("/{hardwareUuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public void createMaintenanceDate(
            @PathVariable UUID hardwareUuid,
            @Valid @RequestBody MaintenanceDateDto maintenanceDateDto
    ) {
        service.addMaintenanceDateByUuid(hardwareUuid, maintenanceDateDto);
    }

    @OnlyAdminAllowed
    @PutMapping("/{dateUuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMaintenanceDate(
            @PathVariable UUID dateUuid,
            @Valid @RequestBody MaintenanceDateDto dateDto
    ) {
        service.updateMaintenanceDate(dateUuid, dateDto);
    }

    @OnlyAdminAllowed
    @DeleteMapping("/{hardwareUuid}/{dateUuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMaintenanceDate(@PathVariable UUID hardwareUuid, @PathVariable UUID dateUuid) {
        service.deleteMaintenanceDateByUuid(hardwareUuid, dateUuid);
    }
}
