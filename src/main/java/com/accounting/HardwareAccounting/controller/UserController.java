package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.configuration.OnlyAdminAllowed;
import com.accounting.HardwareAccounting.user.UserDto;
import com.accounting.HardwareAccounting.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/users")
@OnlyAdminAllowed
public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String showUsers(Model model) {
        List<UserDto> userList = service.getAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/new")
    public String showCreatingForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user_form";
    }

    @GetMapping("/{uuid}/edit")
    public String showEditingForm(@PathVariable("uuid") UUID uuid, Model model) {
        try {
            Optional<UserDto> user = service.getByUuid(uuid);
            model.addAttribute("user", user);
            return "user_form";
        } catch (Exception e) {
            return "redirect:/users";
        }
    }

    @PostMapping
    public String createUser(@RequestBody UserDto dto) {
        service.create(dto);
        return "redirect:/users";
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID uuid, @RequestBody UserDto dto) {
        service.update(uuid, dto);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("uuid") UUID uuid) {
        service.delete(uuid);
    }

}
