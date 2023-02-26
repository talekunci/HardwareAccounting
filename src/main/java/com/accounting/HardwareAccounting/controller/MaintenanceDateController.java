package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.configuration.OnlyAdminAllowed;
import com.accounting.HardwareAccounting.hardware.HardwareServiceImpl;
import com.accounting.HardwareAccounting.hardware.MaintenanceDateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
        try {
            model.addAttribute("datesSet", service.getMaintenanceDatesByUuid(hardwareUuid));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return "redirect:/hardware";
        }

        return "maintenance_dates";
    }

    @OnlyAdminAllowed
    @GetMapping("/new")
    public String showEditingForm(Model model) {
        model.addAttribute("date", new MaintenanceDateDto());

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
    public String createMaintenanceDate(
            @RequestParam UUID hardwareUuid,
            @Valid @RequestBody MaintenanceDateDto maintenanceDateDto,
            Model model
    ) {
        service.addMaintenanceDateByUuid(hardwareUuid, maintenanceDateDto);

        return getMaintenanceDates(hardwareUuid, model);
    }

    @OnlyAdminAllowed
    @PutMapping
    public void updateMaintenanceDate(@Valid @RequestBody MaintenanceDateDto maintenanceDateDto) {
        service.updateMaintenanceDate(maintenanceDateDto);
    }

    @OnlyAdminAllowed
    @DeleteMapping
    public void deleteMaintenanceDate(@RequestParam UUID uuid) {
        service.deleteMaintenanceDateByUuid(uuid);
    }
}
