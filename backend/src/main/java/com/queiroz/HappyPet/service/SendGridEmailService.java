package com.queiroz.HappyPet.service;

import com.queiroz.HappyPet.entities.DTO.EmailDTO;
import com.queiroz.HappyPet.service.exceptions.EmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Slf4j
public class SendGridEmailService implements EmailService {

    @Autowired
    private SendGrid sendGrid;


    @Override
    public void sendEmail(EmailDTO email) {
        Email from = new Email(email.getFromEmail(), email.getFromName());
        Email to = new Email(email.getTo());
        Content content = new Content(email.getContentType(), email.getBody());
        Mail mail = new Mail(from, email.getSubject(), to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            log.info("Enviando e-mail para: " + email.getTo());
            Response response = sendGrid.api(request);
            if (response.getStatusCode() >= 400 && response.getStatusCode() <= 500) {
                log.error("Erro no envio do e-mail" + response.getBody());
                throw new EmailException(response.getBody());
            }
        } catch (IOException e) {
            throw new EmailException(e.getMessage());
        }
    }
}
