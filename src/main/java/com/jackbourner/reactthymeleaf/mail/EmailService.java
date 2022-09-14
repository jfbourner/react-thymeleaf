package com.jackbourner.reactthymeleaf.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    String toEmail;

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public String sendMail(ContactForm contactForm) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Context context = new Context();
        context.setVariable("introduction", "Welcome");
        context.setVariable("contactForm", contactForm);
        context.setVariable("logo", "logo");
        String process = templateEngine.process("mail/email-confirmation", context);

        helper.setSubject("Hi " + contactForm.name);
        helper.setText(process, true);
        helper.setTo(contactForm.email);
        helper.addInline("logo",new ClassPathResource("static/images/jackb.png"),"image/png");

        javaMailSender.send(mimeMessage);

        MimeMessage mimeMessage2 = javaMailSender.createMimeMessage();
        MimeMessageHelper helper2 = new MimeMessageHelper(mimeMessage2, true, "UTF-8");
        helper2.setSubject(contactForm.getSubject());
        helper2.setText(contactForm.getEmail() + ": " + contactForm.message);
        helper2.setTo(toEmail);
        javaMailSender.send(mimeMessage2);

        return "Sent";
    }
}
