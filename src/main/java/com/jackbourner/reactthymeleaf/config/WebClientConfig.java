package com.jackbourner.reactthymeleaf.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.jackbourner.reactthymeleaf.warzone.WarzoneConstants.*;

@Configuration
//@Log
@SuppressWarnings("unused")
public class WebClientConfig {

    @Autowired
    EntityManager entityManager;

    public static final String SSO = "MjMzODU4OTk0ODAxMDkwMTIxMzoxNjY3OTA0MzY0NTE4OjQ1M2RmMzI0MmEyMmY4MjY0NjIyM2I2ZTZmNjY2NWQy";

    @Bean
    public HttpClient createHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .responseTimeout(Duration.ofMillis(10000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient createWebclient(HttpClient httpClient) {
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect(true)
                ))
                .defaultCookie(NEW_SITE_ID, "cod")
                .defaultCookie(COMID, "cod")
                .defaultCookie(ACT_SSO_COOKIE, SSO)
                .defaultCookie(ACT_SSO_EVENT, "LOGIN_SUCCESS:1666694764626")
                .defaultCookie(ACT_SSO_COOKIE_EXPIRY, "1667904364518")
                .defaultCookie(ACT_SSO_LOCALE, "en_GB")
                .defaultCookie(ACT_SSO_REMEMBER_ME, "MjMzODU4OTk0ODAxMDkwMTIxMzokMmEkMTAkbVMyMmhZTHppamFqLkxCYXZ3bjMydXdxNTh0RS5hN085djNPZjI4WFpZaVpVY0hYenUzcEM")
                .defaultHeader("Atvi-Auth", SSO)
                .defaultHeader("CT_SSO_COOKIE", SSO)
                .defaultHeader("atkn", SSO)
                .defaultHeader("ContentType", "application/json")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        String xsfr_token = webClient.get()
                .uri("https://profile.callofduty.com/cod/login")
                .exchangeToMono(res -> {
                    String XSFR_TOKEN = "";
                    XSFR_TOKEN = res.cookies().getFirst("XSRF-TOKEN").getValue();
                    return Mono.just(XSFR_TOKEN);
                })
                .block();
        webClient.mutate()
                .defaultCookie(XSRF_TOKEN, xsfr_token)
                .defaultHeader("X-XSRF-TOKEN", xsfr_token);
        return webClient;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

}
