package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.user.UserDto;
import com.accounting.HardwareAccounting.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService service;

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

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
    public void registerNew(@Valid @RequestBody UserDto dto, HttpServletRequest request) {
        service.create(dto);
        logger.info("User '" + dto.getLogin() + "' has been registered.");
        autoAuth(request, dto.getLogin(), dto.getPassword());
    }

    public void autoAuth(HttpServletRequest request, String login, String pass) {
        try {
            request.login(login, pass);
            logger.info("User '" + login + "' has been logged in after registration.");
        } catch (ServletException e) {
            logger.error("Error during logging in new user.", e);
        }
    }

}