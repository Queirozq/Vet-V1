package com.queiroz.HappyPet.service;

import com.queiroz.HappyPet.entities.DTO.EmailDTO;

public interface EmailService {

    void sendEmail(EmailDTO email);
}
