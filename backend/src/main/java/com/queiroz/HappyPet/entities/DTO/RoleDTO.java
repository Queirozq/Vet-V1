package com.queiroz.HappyPet.entities.DTO;

import com.queiroz.HappyPet.entities.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter
public class RoleDTO implements Serializable {

    private Long id;
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Role obj) {
        this.id = obj.getId();
        this.authority = obj.getAuthority();
    }
}
