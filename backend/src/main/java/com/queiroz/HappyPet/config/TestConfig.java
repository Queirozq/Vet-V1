package com.queiroz.HappyPet.config;

import com.queiroz.HappyPet.service.EmailService;
import com.queiroz.HappyPet.service.MockEmailService;
import com.queiroz.HappyPet.service.SendGridEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {


    @Bean
    public EmailService emailService(){
        return new SendGridEmailService();
    }
}
