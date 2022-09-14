package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.mail.ContactForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings("unused")
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "index";
    }
}