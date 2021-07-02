package com.digitalkrapht.bloodbank.bloodbank.nofications.email.executor;

import com.digitalkrapht.bloodbank.bloodbank.nofications.email.request.Mail;
import com.digitalkrapht.bloodbank.bloodbank.nofications.email.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class EmailExecutor {

    @Autowired
    MailSenderService mailSenderService;


    public void ScheduledMailExecutor(Mail mail, String templateName, int timeInSeconds) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {

            try {

                this.mailSenderService.sendEmail(mail,templateName);

//                mailSenderService.sendRegistrationMail(message, email, password);
            } catch (Exception e) {
                // log the sms to be sent later
            }
        };

        executor.schedule(task, timeInSeconds, TimeUnit.SECONDS);
    }

}
