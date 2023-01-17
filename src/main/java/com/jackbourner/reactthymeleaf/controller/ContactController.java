package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.mail.ContactForm;
import com.jackbourner.reactthymeleaf.mail.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
@SuppressWarnings("unused")
public class ContactController {

    @Autowired
    EmailService emailService;

    @PostMapping("/contactForm")
    public ContactResponse contactFormSubmit(@ModelAttribute ContactForm contactForm) {
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
