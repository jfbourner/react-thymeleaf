package com.jackbourner.reactthymeleaf.mail;

import jakarta.activation.CommandMap;
import jakarta.activation.MailcapCommandMap;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    String toEmail;
    @Autowired private TemplateEngine templateEngine;
    @Autowired private JavaMailSender javaMailSender;

    public String sendMail(ContactForm contactForm) throws MessagingException {
    try {
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("introduction", "Welcome");
        context.setVariable("contactForm", contactForm);
        context.setVariable("logo", "logo");
        String process = templateEngine.process("mail/email-confirmation", context);

        helper.setSubject("Hi " + contactForm.getName());
        helper.setText(process, true);
        helper.setTo(contactForm.getEmail());
        helper.addInline("logo", new ClassPathResource("static/images/jackb.png"), "image/png");
        javaMailSender.send(mimeMessage);

        MimeMessage mimeMessage2 = javaMailSender.createMimeMessage();
        MimeMessageHelper helper2 = new MimeMessageHelper(mimeMessage2, true, "UTF-8");
        helper2.setSubject(contactForm.getSubject());
        helper2.setText(contactForm.getEmail() + ": " + contactForm.getMessage());
        helper2.setTo(toEmail);
        javaMailSender.send(mimeMessage2);
    }catch (Exception e){
        System.out.println(e);
    }
        return "Sent";
    }
}
