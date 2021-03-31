package com.okta.aycPedidos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping("/login")
    public String login(ModelMap modelo) {
        return "login.html";
    }
	
}
