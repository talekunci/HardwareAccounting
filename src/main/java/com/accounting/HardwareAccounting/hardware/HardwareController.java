package com.accounting.HardwareAccounting.hardware;

import com.accounting.HardwareAccounting.user.UserDto;
import com.accounting.HardwareAccounting.user.UserServiceImpl;
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
public class HardwareController {

  @Autowired
  private HardwareServiceImpl service;

  @GetMapping("/users")
  public String showHardware(Model model) {
    List<HardwareDto> hardwareList = service.getAll();
    model.addAttribute("hardwareList", hardwareList);
    return "hardware";
  }

  @GetMapping("/hardware/new")
  public String showCreatingForm(Model model) {
    return "redirect:/hardware";
  }

  @PostMapping("/hardware/save")
  public String createHardware(HardwareDto hardware) {
    service.create(hardware);
    return "redirect:/hardware";
  }

  @GetMapping
  public String showEditingForm(@PathVariable("uuid") UUID uuid, Model model) {
    try {
      Optional<HardwareDto> hardware = service.getByUuid(uuid);
      model.addAttribute("hardware", hardware);
      return "hardware_form";
    } catch (Exception e) {
      return "redirect:/hardware";
    }
  }

  @GetMapping("/hardware/delete/{uuid}")
  public String deleteHardware(@PathVariable("uuid") UUID uuid) {
    service.delete(uuid);
    return "redirect:/hardware";
  }

}