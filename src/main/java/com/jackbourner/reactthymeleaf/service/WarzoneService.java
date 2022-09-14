package com.jackbourner.reactthymeleaf.service;

import com.jackbourner.reactthymeleaf.warzone.WarzoneApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarzoneService {

    @Autowired
    WarzoneApi warzoneApi;

    public boolean login(){
        String XSFR_TOKEN = warzoneApi.getCSRFToken();
        warzoneApi.activisionLogin(XSFR_TOKEN);
        return true;
    }
}
