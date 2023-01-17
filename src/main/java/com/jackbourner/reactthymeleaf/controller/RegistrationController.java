package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.dto.UserDto;
import com.jackbourner.reactthymeleaf.model.UserAccount;
import com.jackbourner.reactthymeleaf.service.CustomUserDetailsService;
import com.jackbourner.reactthymeleaf.service.UserAlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Log
@SuppressWarnings("unused")
public class RegistrationController {

    @Autowired
    CustomUserDetailsService userService;

    @GetMapping("/registration")
    public String registration(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
        //log.info(userDto.toString());
        boolean existingEmail = userService.emailExists(userDto.getEmail());

        if(existingEmail){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        boolean existingUsername = userService.usernameExists(userDto.getUsername());
        if(existingUsername){
            result.rejectValue("username", null, "There is already an account registered with the same username");
        }

        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("registration");
            mav.addObject("user", userDto);
            return mav;
        }

        try {
            UserAccount registered = userService.registerNewUserAccount(userDto);
        }
        catch(DataAccessException | UserAlreadyExistException ex){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error");
            mav.addObject("errorMsg", "An error occurred during registration. Please contact the administrator");
            return mav;
        }
        return new ModelAndView("successRegister", "user", userDto);
    }
}
