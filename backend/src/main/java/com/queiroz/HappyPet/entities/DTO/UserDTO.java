package com.queiroz.HappyPet.entities.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.queiroz.HappyPet.entities.User;
import lombok.Getter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
public class UserDTO implements Serializable {

    private Long id;
    @NotBlank
    @Size(min = 5, max = 20, message = "Campo deve ter entre 5 e 20 caracteres")
    private String name;
    @NotBlank
    @Email(message = "Favor entrar com um email válido")
    private String email;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }


    public UserDTO(User obj){
       id = obj.getId();
       name = obj.getName();
       email = obj.getEmail();
       obj.getRoles().forEach(x-> roles.add(new RoleDTO(x)));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
