package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.mail.ContactForm;
import com.jackbourner.reactthymeleaf.mail.EmailService;
import com.jackbourner.reactthymeleaf.recaptcha.IReCaptchaService;
import jakarta.mail.MessagingException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
@SuppressWarnings("unused")
public class ContactController {

    @Autowired
    EmailService emailService;

    @Autowired
    IReCaptchaService reCaptchaService;

    @PostMapping("/contactForm")
    public ContactResponse contactFormSubmit(@ModelAttribute ContactForm contactForm, @RequestParam(name = "g-recaptcha-response") String response){
        float score  = reCaptchaService.processResponse(response);
        if (score < 0.5) {
            log.warning("Recaptcha score is " + score);
            return ContactResponse.builder()
                    .status(ContactResponse.Status.DANGER)
                    .message("Recaptcha failed. Either you're a bot or Recaptcha is in error")
                    .build();
        }
        log.info(contactForm.toString());
        try {
            emailService.sendMail(contactForm);
        }catch (MessagingException | RuntimeException e) {
            return ContactResponse.builder()
                    .status(ContactResponse.Status.DANGER)
                    .message(e.getMessage())
                    .build();
        }
        return ContactResponse.builder()
                .status(ContactResponse.Status.SUCCESS)
                .message("Thanks for your email " + contactForm.getName() + ". I will get back to you :)")
                .build();
    }
}
