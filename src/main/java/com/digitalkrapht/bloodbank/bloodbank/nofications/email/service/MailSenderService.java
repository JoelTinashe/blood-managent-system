package com.digitalkrapht.bloodbank.bloodbank.nofications.email.service;


import com.digitalkrapht.bloodbank.bloodbank.nofications.email.request.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    @Qualifier("primary")
    private SpringTemplateEngine templateEngine;


    public void sendEmail(Mail mail, String templateName) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(mail.getProps());

        String html = templateEngine.process(templateName, context);

        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom("support@digitalkrapht.com");

        emailSender.send(message);
    }
}
