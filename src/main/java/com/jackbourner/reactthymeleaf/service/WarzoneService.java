package com.jackbourner.reactthymeleaf.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jackbourner.reactthymeleaf.dto.WarzoneRequest;
import com.jackbourner.reactthymeleaf.warzone.WarzoneApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WarzoneService {

    @Autowired
    WarzoneApi warzoneApi;

    public ObjectNode getFullData(String gamerTag){
        return warzoneApi.fullData(gamerTag);
    }
    public ObjectNode getCombatHistoryData(String gamerTag){
        return warzoneApi.combatHistory(gamerTag);
    }

    public ObjectNode getcombatHistoryWithDate(WarzoneRequest warzoneRequest){
        return warzoneApi.combatHistoryWithDate(
                warzoneRequest.getUsername(),
                dateTimeToEpochMills(warzoneRequest.getDateFrom()),
                dateTimeToEpochMills(warzoneRequest.getDateTo())
        );
    }

    private String dateTimeToEpochMills(LocalDateTime dateTime){
        long dt = dateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
        return Long.toString(dt);
    }

}
