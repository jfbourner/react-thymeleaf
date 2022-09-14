package com.jackbourner.reactthymeleaf.data;

public record WarzoneLoginRequest(String username, String password, String remember_me, String _csrf) {

}
