package com.queiroz.HappyPet.entities.DTO;

import com.queiroz.HappyPet.entities.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter@Setter@NoArgsConstructor
public class AnimalDTO implements Serializable {

    private Long id;
    private String name;
    @NotBlank
    @Size(min = 2, max = 15, message = "Campo deve ter entre 5 e 15 caracteres")
    private String animal;
    @NotBlank
    @Size(min = 1, max = 1, message = "Campo só pode ter 1 caractere")
    private String gender;
    @NotBlank
    @Size(min = 5, max = 15, message = "Campo deve ter entre 5 e 15 caracteres")
    private String race;
    private String photo;

    private UserDTO owner;


    public AnimalDTO(Animal obj) {
        this.name = obj.getName();
        this.animal = obj.getAnimal();
        this.gender = obj.getGender();
        this.race = obj.getRace();
        this.id = obj.getId();
        this.owner = new UserDTO(obj.getOwner());
        this.photo = obj.getPhoto();
    }

}
