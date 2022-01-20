package com.queiroz.HappyPet.resources;

import com.queiroz.HappyPet.entities.DTO.AnimalDTO;
import com.queiroz.HappyPet.entities.DTO.AnimalDTOUpdate;
import com.queiroz.HappyPet.entities.DTO.UriDTO;
import com.queiroz.HappyPet.service.AnimalService;
import com.queiroz.HappyPet.service.exceptions.DatabaseException;
import com.queiroz.HappyPet.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/animals")
public class AnimalResource {

    @Autowired
    private AnimalService service;

    @GetMapping
    public ResponseEntity<Page<AnimalDTO>>findAll(Pageable pageable){
        Page<AnimalDTO> page = service.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> findById(@PathVariable Long id){
        AnimalDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> insert(@Valid @RequestBody AnimalDTO obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AnimalDTOUpdate> update(@Valid @RequestBody AnimalDTOUpdate obj, @PathVariable Long id){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível deletar esse produto");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Animal não encontrado");
        }
    }

    @PostMapping(value = "/image/{id}")
    public ResponseEntity<UriDTO> uploadImageInsert(@RequestParam("file")MultipartFile file, @PathVariable Long id){
        UriDTO uri = service.uploadFile(file, id);
        return ResponseEntity.ok().body(uri);
    }
}
