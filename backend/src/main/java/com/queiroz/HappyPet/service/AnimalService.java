package com.queiroz.HappyPet.service;

import com.queiroz.HappyPet.entities.Animal;
import com.queiroz.HappyPet.entities.DTO.AnimalDTO;
import com.queiroz.HappyPet.entities.DTO.AnimalDTOUpdate;
import com.queiroz.HappyPet.entities.DTO.UriDTO;
import com.queiroz.HappyPet.repositories.AnimalRepository;
import com.queiroz.HappyPet.repositories.UserRepository;
import com.queiroz.HappyPet.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.net.URL;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3Service s3Service;

    @Transactional(readOnly = true)
    public Page<AnimalDTO> findAll(Pageable pageable) {
        Page<Animal> animalPage = repository.findAll(pageable);
        return animalPage.map(x -> new AnimalDTO(x));
    }

    @Transactional(readOnly = true)
    public AnimalDTO findById(Long id) {
        Optional<Animal> obj = repository.findById(id);
        Animal animal = obj.orElseThrow(() -> new ResourceNotFoundException("Cod não encontrado"));
        return new AnimalDTO(animal);
    }


    @Transactional
    public AnimalDTO insert(AnimalDTO obj) {
        Animal animal = new Animal();
        fromDTO(animal, obj);
        animal = repository.save(animal);
        return new AnimalDTO(animal);
    }

    @Transactional
    public AnimalDTOUpdate update(Long id, AnimalDTOUpdate obj) {
        try {
            Animal animal = repository.getById(id);
            fromDTOUpdate(animal, obj);
            animal = repository.save(animal);
            return new AnimalDTOUpdate(animal);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Animal não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }


    @Transactional
    public UriDTO uploadFile(MultipartFile file, Long id) {
        URL url = s3Service.uploadFile(file);
        Animal animal = repository.getById(id);
        animal.setPhoto(url.toString());
        repository.save(animal);
        return new UriDTO(url.toString());
    }


    public void fromDTO(Animal obj, AnimalDTO objDTO) {
        obj.setAnimal(objDTO.getAnimal());
        obj.setName(objDTO.getName());
        obj.setGender(objDTO.getGender());
        obj.setRace(objDTO.getRace());
        obj.setPhoto(objDTO.getPhoto());
        obj.setOwner(userRepository.getById(objDTO.getOwner().getId()));
    }

    public void fromDTOUpdate(Animal obj, AnimalDTOUpdate objDTO) {
        obj.setAnimal(objDTO.getAnimal() != null ? objDTO.getAnimal() : obj.getAnimal());
        obj.setName(objDTO.getName() != null ? objDTO.getName() : obj.getName());
        obj.setGender(objDTO.getGender() != null ? objDTO.getGender() : obj.getGender());
        obj.setRace(objDTO.getRace() != null ? objDTO.getRace() : obj.getRace());
        obj.setPhoto(objDTO.getPhoto() != null ? objDTO.getPhoto() : obj.getPhoto());
    }
}
