package com.jackbourner.reactthymeleaf.warzone;

public enum Platforms {

    All("all"),
    Activision("acti"),
    Battlenet("battle"),
    PSN("psn"),
    Steam("steam"),
    Uno("uno"),
    XBOX("xbl");

    private final String value;

    Platforms(String value){
        this.value = value;
    }

}
