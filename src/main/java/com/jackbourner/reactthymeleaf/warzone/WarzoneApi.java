package com.jackbourner.reactthymeleaf.warzone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Component
public class WarzoneApi {

    @Autowired
    WebClient webClient;

    public String getCSRFToken(){
        //CsrfToken token =session.getAttribute("HttpSessionCsrfTokenRepository.CSRF_TOKEN");
        ResponseEntity<Void> result =
                webClient.get()
                        .uri("https://s.activision.com/activision/login")
                        .retrieve()
                        .toBodilessEntity()
                        .block();

        String XSFR_TOKEN = "";
        if (Objects.nonNull(result) && Objects.nonNull(result.getHeaders())) {
            HttpHeaders headers =result.getHeaders();
            List<String> s = headers.get("Set-Cookie");
            XSFR_TOKEN = Objects.nonNull(s) ? s.get(0).split(";")[0] : "";
        }
        return XSFR_TOKEN;
    }

    public String activisionLogin(String XSFR_TOKEN){

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "jackbourner@hotmail.co.uk");
        formData.add("password", "Jackannamillie1");
        formData.add("remember_me", "true");
        formData.add("_csrf", XSFR_TOKEN.split("=")[1]);

        ResponseEntity<String> result = webClient.post()
                .uri("https://s.activision.com/do_login",  uriBuilder -> uriBuilder
                        .queryParam("new_SiteId", "activision")
                        .build())
                .header("Cookie:" + XSFR_TOKEN + ";" + "new_SiteId" + "=" + "activision")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .toEntity(String.class)
                .block();
        System.out.println(result);
        return "";

    }
    //GETInitialize
    //https://s.activision.com/activision/login
    //Initialize the client by fetching CSRF token from the server

    //POSTLogin
    //Authenticate the client by exchanging CSRF token and login credentials for SSO and ATKN tokens

    //POST2FA
    //Authenticate the client by exchanging CSRF token and login credentials for SSO and ATKN tokens


    //GETIdentity
    //Retrieve game and platform identification for the authenticated client


    //GETUser Info
    //https://profile.callofduty.com/cod/userInfo/Set by test scripts

    //GETPlatforms
    //Retrieve all platform identifiers associated with the authenticated client

    //GETFriends
    //Retrieve friends list with platform identifiers for the authenticated client

    //GETFriends Profiles
    //Retrieve all platform identifiers associated with the authenticated client

    //POSTSet Profile Visibility

    //POSTSet Search Visibility

    //GETWarzone Profile
    //Fetch anyone's Warzone profile


    //GETWarzone Matches

    //GETMatch Details
    //    Retrieve game and platform identification for the authenticated client
}
