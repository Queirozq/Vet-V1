package com.queiroz.HappyPet.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class FileDTO implements Serializable {

    private MultipartFile file;
}
