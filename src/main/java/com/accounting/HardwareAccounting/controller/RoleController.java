package com.accounting.HardwareAccounting.controller;

import com.accounting.HardwareAccounting.configuration.OnlyAdminAllowed;
import com.accounting.HardwareAccounting.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@OnlyAdminAllowed
@RequestMapping("/users/{uuid}/roles")
public class RoleController {

    private final UserServiceImpl service;

    public RoleController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(@PathVariable UUID uuid, Model model) {
        service.getByUuid(uuid)
                .ifPresent(userDto -> model.addAttribute("roleSet", service.getRolesByUuid(uuid)));
        model.addAttribute("allRolesSet", service.getAllRoles());
        return "user_roles";
    }

    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void addRole(@PathVariable UUID uuid, @PathVariable Long roleId) {
        service.addRoleById(uuid, roleId);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRole(@PathVariable UUID uuid, @PathVariable Long roleId) {
        service.deleteRoleById(uuid, roleId);
    }

}
