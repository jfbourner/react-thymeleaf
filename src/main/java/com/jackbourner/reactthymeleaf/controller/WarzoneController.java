package com.jackbourner.reactthymeleaf.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jackbourner.reactthymeleaf.dto.WarzoneRequest;
import com.jackbourner.reactthymeleaf.service.WarzoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@SuppressWarnings("unused")
public class WarzoneController {

    @Autowired
    WarzoneService warzoneService;

    @PostMapping("/warzone")
    public String warzoneCombatHistoryWithDate(@ModelAttribute @Valid WarzoneRequest warzoneRequest, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ObjectNode data = warzoneService.getcombatHistoryWithDate(warzoneRequest);
            model.addAttribute("data", data);
        }
        model.addAttribute("platformList",  List.of("psn","battlenet", "xbl"));
        model.addAttribute("warzoneRequest", warzoneRequest);

        return "warzone";
    }

    @GetMapping("/warzone")
    public String warzone(Model model) {
        model.addAttribute("warzoneRequest", new WarzoneRequest());
        model.addAttribute("platformList",  List.of("psn","battlenet", "xbl"));
        model.addAttribute("data", null);
        return "warzone";
    }
}
