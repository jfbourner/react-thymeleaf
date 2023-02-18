package com.jackbourner.reactthymeleaf.mail;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ContactForm {
    private String name, email, subject, message;
}