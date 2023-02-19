package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.config.CaptchaSettings;
import com.jackbourner.reactthymeleaf.mail.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings("unused")
public class HomeController {

    @Autowired
    CaptchaSettings captchaSettings;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        model.addAttribute("siteKey",captchaSettings.site());
        return "index";
    }
}