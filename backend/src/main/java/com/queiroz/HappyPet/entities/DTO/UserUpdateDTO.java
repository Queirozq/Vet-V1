package com.queiroz.HappyPet.entities.DTO;

import com.queiroz.HappyPet.entities.User;
import com.queiroz.HappyPet.service.validation.UserUpdateValid;
import lombok.NoArgsConstructor;


@UserUpdateValid
public class UserUpdateDTO extends UserDTO{


    public UserUpdateDTO() {
    }

    public UserUpdateDTO(User obj) {
        super(obj);
    }
}
