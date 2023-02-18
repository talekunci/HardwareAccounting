package com.accounting.HardwareAccounting.conroller;

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

    @GetMapping("/new")
    public String showCreatingForm(Model model) {
        model.addAttribute("hardware", new Hardware());
        return "hardware_form";
    }

    @PostMapping
    public String createHardware(@Valid @RequestBody HardwareDto dto) {
        service.create(dto);
        return "redirect:/hardware";
    }

    public String update(@Valid @RequestBody HardwareDto dto) {
        service.update(dto.getUuid(), dto);
        return "redirect:/hardware";
    }

    @GetMapping("/edit")
    public String showEditingForm(@RequestParam("uuid") UUID uuid, Model model) {
        try {
            Optional<HardwareDto> hardware = service.getByUuid(uuid);
            model.addAttribute("hardware", hardware);
            return "hardware_form";
        } catch (Exception e) {
            return "redirect:/hardware";
        }
    }

    @DeleteMapping
    public String deleteHardware(@RequestParam("uuid") UUID uuid) {
        service.delete(uuid);
        return "redirect:/hardware";
    }

}