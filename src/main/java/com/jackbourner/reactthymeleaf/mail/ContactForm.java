package com.jackbourner.reactthymeleaf.mail;

import lombok.*;

@AllArgsConstructor
@Data
@Getter
@Setter
@NoArgsConstructor
public class ContactForm {
    String name, email, subject, message;
}