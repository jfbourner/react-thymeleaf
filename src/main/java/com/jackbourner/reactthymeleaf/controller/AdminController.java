package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.dto.UserDto;
import com.jackbourner.reactthymeleaf.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
//@Log
public class AdminController {

    @Autowired
    CustomUserDetailsService userDetails;

    @GetMapping("/admin")
    public String registration(WebRequest request, Model model) {
        List<UserDto> userDto = userDetails.getAllUsers();
        model.addAttribute("users", userDto);
        return "admin";
    }
}
