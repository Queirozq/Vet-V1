package com.queiroz.HappyPet.resources;

import com.queiroz.HappyPet.entities.DTO.EmailDTO;
import com.queiroz.HappyPet.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {

    @Autowired
    private EmailService service;

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody EmailDTO email){
        service.sendEmail(email);
        return ResponseEntity.noContent().build();
    }
}
