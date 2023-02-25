package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.configuration.OnlyAdminAllowed;
import com.accounting.HardwareAccounting.hardware.Hardware;
import com.accounting.HardwareAccounting.hardware.HardwareDto;
import com.accounting.HardwareAccounting.hardware.HardwareServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/hardware")
public class HardwareController {

    private final HardwareServiceImpl service;

    public HardwareController(HardwareServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String showHardwareList(Model model) {
        List<HardwareDto> hardwareList = service.getAll();
        model.addAttribute("hardwareList", hardwareList);
        return "hardware";
    }

    @GetMapping("/{uuid}")
    public String showOneHardware(@PathVariable UUID uuid, Model model) {
        return showEditingForm(uuid, model);
    }

    @OnlyAdminAllowed
    @GetMapping("/new")
    public String showCreatingForm(Model model) {
        model.addAttribute("hardware", new HardwareDto());
        return "hardware_form";
    }

    @OnlyAdminAllowed
    @GetMapping("/edit")
    public String showEditingForm(@RequestParam("uuid") UUID uuid, Model model) {
        Optional<HardwareDto> hardware = service.getByUuid(uuid);
        if (hardware.isPresent()) {
            model.addAttribute("hardware", hardware.get());
        } else {
            System.out.printf("Hardware with uuid = '%s' not found.", uuid.toString());
            return "redirect:/hardware";
        }
        return "hardware_form";
    }

    @OnlyAdminAllowed
    @PostMapping ("/save")
    public String saveHardware(HardwareDto dto) {
        service.create(dto);
        return "redirect:/hardware";
    }

    @OnlyAdminAllowed
    @PutMapping
    public String update(@Valid @RequestBody HardwareDto dto) {
        service.update(dto.getUuid(), dto);
        return "redirect:/hardware";
    }

    @OnlyAdminAllowed
    @DeleteMapping
    public String deleteHardware(@RequestParam("uuid") UUID uuid) {
        service.delete(uuid);
        return "redirect:/hardware";
    }

}