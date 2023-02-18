package com.jackbourner.reactthymeleaf.security;

import com.jackbourner.reactthymeleaf.model.RecaptchaResponse;
import com.jackbourner.reactthymeleaf.recaptcha.RecaptchaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.POST;
import jakarta.xml.bind.ValidationException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log
@Component
public class RecaptchaFilter extends OncePerRequestFilter {

    @Autowired
    RecaptchaService recaptchaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //log.info("Recaptcha  filter was called");
       /* if (request.getMethod().equals(RequestMethod.POST.name())) {
            String recaptcha = request.getParameter("g-recaptcha-response");
            float score = recaptchaService.processResponse(recaptcha);
            if (score < 0.5) {
                log.warning("Recaptcha score is " + score);
                request.getRequestDispatcher("index").forward(request, response);
            }
        }*/
        filterChain.doFilter(request, response);
    }
}
