package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.service.WarzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class WarzoneController {

    @Autowired
    WarzoneService warzoneService;

    @GetMapping("/warzone-login")
    public boolean warzoneLogin(@Param("username") String username, @Param("platform") String platform){
        warzoneService.login();
        return true;
    }
}
