package com.queiroz.HappyPet.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class EmailDTO implements Serializable {

    private String fromEmail;
    private String fromName;
    private String replyTo;
    private String to;
    private String subject;
    private String body;
    private String contentType;
}
