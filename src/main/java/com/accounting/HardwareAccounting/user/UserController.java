package com.accounting.HardwareAccounting.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  @Autowired
  private UserServiceImpl service;

  @GetMapping("/users")
  public String showUsers(Model model) {
    List<UserDto> userList = service.getAll();
    model.addAttribute("userList", userList);
    return "users";
  }

  @GetMapping("/users/new")
  public String showCreatingForm(Model model) {
    return "redirect:/users";
  }

  @PostMapping("/users/save")
  public String createUser(UserDto user) {
    service.create(user);
    return "redirect:/users";
  }

  @GetMapping
  public String showEditingForm(@PathVariable("uuid") UUID uuid, Model model) {
    try {
      Optional<UserDto> user = service.getByUuid(uuid);
      model.addAttribute("user", user);
      return "user_form";
    } catch (Exception e) {
      return "redirect:/users";
    }
  }

  @GetMapping("/users/delete/{uuid}")
  public String deleteUser(@PathVariable("uuid") UUID uuid) {
    service.delete(uuid);
    return "redirect:/users";
  }

}
