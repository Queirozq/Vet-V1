package com.queiroz.HappyPet.entities.DTO;

import com.queiroz.HappyPet.entities.User;
import com.queiroz.HappyPet.service.validation.UserInsertValid;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@UserInsertValid
public class UserInsertDTO extends UserDTO{

    private String password;

    public UserInsertDTO() {
    }

    public UserInsertDTO(User obj, String password) {
        super(obj);
        this.password = password;
    }
}
