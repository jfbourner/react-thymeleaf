package com.jackbourner.reactthymeleaf.warzone;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.jackbourner.reactthymeleaf.warzone.WarzoneConstants.API_PATH;
import static com.jackbourner.reactthymeleaf.warzone.WarzoneConstants.BASE_URL;

@Component
public class WarzoneApi {

    @Autowired
    WebClient webClient;
    String fullDataPath = "/stats/cod/v1/title/mw/platform/psn/gamer/%s/profile/type/wz";
    String combatHistoryPath = "/crm/cod/v2/title/mw/platform/psn/gamer/%s/matches/wz/start/0/end/0/details";
    String combatHistoryWithDatePath = "/crm/cod/v2/title/mw/platform/psn/gamer/%s/matches/wz/start/%s/end/%s/details";
    String breakdownPath = "/crm/cod/v2/title/mw/platform/psn/gamer/%s/matches/wz/start/0/end/0";
    String breakdownWithDatePath = "/crm/cod/v2/title/mw/platform/psn/gamer/%s/matches/wz/start/%s/end/%s";
    String matchInfoPath = "/crm/cod/v2/title/mw/platform/psn/fullMatch/wz/%s/en";

    public ObjectNode fullData(String gamerTag){
        ObjectNode results = getResults(String.format(fullDataPath, gamerTag));
        return results;
    }

    public ObjectNode combatHistory(String gamerTag){
        ObjectNode results = getResults(String.format(combatHistoryPath, gamerTag));
        return results;
    }

    public ObjectNode combatHistoryWithDate(String gamerTag, String fromDate, String toDate){
        ObjectNode results = getResults(String.format(combatHistoryWithDatePath, gamerTag, fromDate, toDate));
        return results;
    }

    public ObjectNode breakdown(String gamerTag){
        ObjectNode results = getResults(String.format(breakdownPath, gamerTag));
        return results;
    }

    public ObjectNode breakdownWithDate(String gamerTag, String fromDate, String toDate){
        ObjectNode results = getResults(String.format(breakdownWithDatePath, gamerTag, fromDate, toDate));
        return results;
    }

    public ObjectNode matchInfo(String gamerTag){
        ObjectNode results = getResults(String.format(matchInfoPath, gamerTag));
        return results;
    }

    private ObjectNode getResults(String URL){
        ObjectNode result = webClient
                .get().uri(BASE_URL + API_PATH + URL)
                .retrieve()
                .bodyToMono(ObjectNode.class)
                .block();
        System.out.println(result);
        return result;
    }
}
