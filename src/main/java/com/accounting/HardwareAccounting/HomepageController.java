package com.accounting.HardwareAccounting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

  @GetMapping("")
  public String getHomepage() {
    return "homepage";
  }

}