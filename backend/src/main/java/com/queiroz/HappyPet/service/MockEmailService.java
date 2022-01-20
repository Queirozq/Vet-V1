package com.queiroz.HappyPet.service;

import com.queiroz.HappyPet.entities.DTO.EmailDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockEmailService implements EmailService{


    @Override
    public void sendEmail (EmailDTO emailDTO){
        log.info("Sending email to: " + emailDTO.getTo());
        log.info("Email Sent");
    }
}
