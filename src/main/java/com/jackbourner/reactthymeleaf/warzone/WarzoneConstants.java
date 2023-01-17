package com.jackbourner.reactthymeleaf.warzone;

public interface WarzoneConstants {
    String BASE_URL = "https://my.callofduty.com";
    String API_PATH = "/api/papi-client";
    String platform = "psn";
    String warzonePath = "/stats/cod/v1/title/mw/platform/psn/gamer/Ap0c4LyPtO_uK/profile/type/wz";

    //Cookies
    String XSRF_TOKEN = "XSRF-TOKEN";
    String NEW_SITE_ID = "new_SiteId";
    String ATKN = "atkn";
    String COMID = "comid";
    String ACT_SSO_COOKIE = "ACT_SSO_COOKIE";
    String ACT_SSO_EVENT = "ACT_SSO_EVENT";
    String ACT_SSO_COOKIE_EXPIRY = "ACT_SSO_COOKIE_EXPIRY";
    String ACT_SSO_LOCALE = "ACT_SSO_LOCALE";
    String ACT_SSO_REMEMBER_ME = "ACT_SSO_REMEMBER_ME";


    //Headers
    String KEEP_ALIVE = "keep-alive";
}
