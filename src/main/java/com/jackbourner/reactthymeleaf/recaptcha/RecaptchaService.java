package com.jackbourner.reactthymeleaf.recaptcha;

import com.jackbourner.reactthymeleaf.config.CaptchaSettings;
import com.jackbourner.reactthymeleaf.model.RecaptchaResponse;
import com.jackbourner.reactthymeleaf.security.ReCaptchaInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Slf4j

public class RecaptchaService implements IReCaptchaService {
    @Autowired
    private CaptchaSettings captchaSettings;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @RegisterReflectionForBinding(RecaptchaResponse.class)
    public float processResponse(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret",captchaSettings.secret());
        map.add("response",token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,headers);
        RecaptchaResponse response;
        log.debug("Request {}",request);
        try {
            response = restTemplate.postForObject(captchaSettings.url(), request, RecaptchaResponse.class);
        }catch (Exception rce){
            log.error("Error calling recaptcha", rce);
            response = new RecaptchaResponse(false, 0.0f, "","","");
        }

        log.info("Response: {}",response);

       /* if (response.getErrorCodes() != null){
            System.out.println("Error codes: ");
            for (String errorCode: response.getErrorCodes()){
                System.out.println("\t" + errorCode);
            }
        }*/
        if (Objects.nonNull(response)){
            return response.score();
        }else {
            log.error("RecaptchaResponse is Null when it should be RecaptchaResponse object");
            throw new ReCaptchaInvalidException("RecaptchaResponse NULL!");
        }
    }
}
