package com.accounting.HardwareAccounting.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomepageController {

  @GetMapping
  public String getHomepage() {
    return "homepage";
  }

}