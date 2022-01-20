package com.queiroz.HappyPet.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class StandardError implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'~'HH:mm:ss", timezone = "GMT-3")
    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
