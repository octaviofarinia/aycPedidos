package com.okta.aycPedidos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author octav
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index(ModelMap modelo) {
        return "index.html";
    }
}