package com.queiroz.HappyPet.entities.DTO;

import com.queiroz.HappyPet.entities.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter@Setter
public class AnimalDTOUpdate{

    private Long id;
    private String name;
    @Size(min = 2, max = 15, message = "Campo deve ter entre 5 e 15 caracteres")
    private String animal;
    @Size(min = 1, max = 1, message = "Campo só pode ter 1 caractere")
    private String gender;
    @Size(min = 5, max = 15, message = "Campo deve ter entre 5 e 15 caracteres")
    private String race;
    private String photo;


    public AnimalDTOUpdate(Animal obj) {
        this.name = obj.getName();
        this.id = obj.getId();
        this.animal = obj.getAnimal();
        this.gender = obj.getGender();
        this.race = obj.getRace();
        this.photo = obj.getPhoto();
    }
}
