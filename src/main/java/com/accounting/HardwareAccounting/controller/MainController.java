package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.user.UserDto;
import com.accounting.HardwareAccounting.user.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService service;

    public MainController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String getHomepage() {
        return "redirect:/hardware";
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