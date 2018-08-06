package mkopa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping("/api")
  public String index() {
    return "Random String Generator - Marcin Kopa";
  }

}