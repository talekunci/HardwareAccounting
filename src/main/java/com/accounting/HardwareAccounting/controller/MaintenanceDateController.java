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
@RequestMapping("/hardware/{hardwareUuid}/maintenanceDates")
public class MaintenanceDateController {

    private final HardwareServiceImpl service;

    public MaintenanceDateController(HardwareServiceImpl service) {
        this.service = service;
    }

    @GetMapping
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
    @GetMapping("/add")
    public String showCreatingForm(@PathVariable UUID hardwareUuid, Model model) {
        Optional<HardwareDto> hardwareDto = service.getByUuid(hardwareUuid);

        if (hardwareDto.isPresent()) {
            var date = new MaintenanceDateDto();
            model.addAttribute("date", date);
            model.addAttribute("hardware", hardwareDto.get());
        } else {
            return getMaintenanceDates(hardwareUuid, model);
        }

        return "maintenance_date_form";
    }

    @OnlyAdminAllowed
    @GetMapping("/{dateUuid}/edit")
    public String showEditingForm(@PathVariable UUID dateUuid, Model model) {
        Optional<MaintenanceDateDto> dateOptional = service.getMaintenanceDateByUuid(dateUuid);
        if (dateOptional.isPresent()) {
            var dateDto = dateOptional.get();
            model.addAttribute("date", dateDto);
            model.addAttribute("hardware", dateDto.getHardware());
        } else {
            return getMaintenanceDates(dateUuid, model);
        }

        return "maintenance_date_form";
    }

    @OnlyAdminAllowed
    @PostMapping
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
            @RequestBody MaintenanceDateDto dateDto
    ) {
        service.updateMaintenanceDate(dateUuid, dateDto);
    }

    @OnlyAdminAllowed
    @DeleteMapping("/{dateUuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMaintenanceDate(@PathVariable UUID hardwareUuid, @PathVariable UUID dateUuid) {
        service.deleteMaintenanceDateByUuid(hardwareUuid, dateUuid);
    }
}
