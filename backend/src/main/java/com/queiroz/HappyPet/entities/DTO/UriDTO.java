package com.queiroz.HappyPet.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
public class UriDTO implements Serializable {

    private String url;
}
