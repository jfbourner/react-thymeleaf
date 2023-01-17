package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.dto.UserDto;
import com.jackbourner.reactthymeleaf.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@Log
public class UserController {

    @Autowired
    CustomUserDetailsService userDetails;

    @GetMapping("/user")
    public String registration(Authentication authentication, Model model) {
        UserDto user = userDetails.getUserDetails(authentication.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
