package com.accounting.HardwareAccounting.conroller;

import com.accounting.HardwareAccounting.user.UserDto;
import com.accounting.HardwareAccounting.user.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/users")
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
        return "redirect:/users";
    }

    @PostMapping
    public String createUser(UserDto user) {
        service.create(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String showEditingForm(@RequestParam("uuid") UUID uuid, Model model) {
        try {
            Optional<UserDto> user = service.getByUuid(uuid);
            model.addAttribute("user", user);
            return "user_form";
        } catch (Exception e) {
            return "redirect:/users";
        }
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("uuid") UUID uuid) {
        service.delete(uuid);
        return "redirect:/users";
    }

}
