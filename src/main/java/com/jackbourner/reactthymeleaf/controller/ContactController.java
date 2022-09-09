package com.jackbourner.reactthymeleaf.controller;

import com.jackbourner.reactthymeleaf.mail.ContactForm;
import com.jackbourner.reactthymeleaf.mail.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@Log4j2
public class ContactController {

    @Autowired
    EmailService emailService;

    @PostMapping("/contactForm")
    public ContactResponse contactFormSubmit(@ModelAttribute ContactForm contactForm, Model model) {
        log.info(contactForm);
        try {
            emailService.sendMail(contactForm);
        }catch (MessagingException e) {
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

    /*@GetMapping
    public String contactFormAck(Model model){
        //If the email was sent successfully then replace with success + details
        //else send error message

    }*/
}
