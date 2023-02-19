package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.user.UserDto;
import com.accounting.HardwareAccounting.user.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {

    private final UserService service;

    public MainController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getHomepage() {
        return "homepage";
    }

    @GetMapping("/register")
    public String registerView() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNew(@Valid @RequestBody UserDto dto) {
        service.create(dto);
        return "redirect:/hardware";
    }

}