package com.queiroz.HappyPet.resources;

import com.queiroz.HappyPet.entities.DTO.UserDTO;
import com.queiroz.HappyPet.entities.DTO.UserInsertDTO;
import com.queiroz.HappyPet.entities.DTO.UserUpdateDTO;
import com.queiroz.HappyPet.service.UserService;
import com.queiroz.HappyPet.service.exceptions.DatabaseException;
import com.queiroz.HappyPet.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;


    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){
        Page<UserDTO> page = service.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO user = service.findByCode(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserInsertDTO user){
        UserDTO userDTO = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO user){
        UserDTO userDTO = service.update(id, user);
        return ResponseEntity.ok().body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível deletar esse produto");
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Animal não encontrado");
        }
    }
}
