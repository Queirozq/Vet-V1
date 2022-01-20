package com.queiroz.HappyPet.service;

import com.queiroz.HappyPet.entities.DTO.RoleDTO;
import com.queiroz.HappyPet.entities.DTO.UserDTO;
import com.queiroz.HappyPet.entities.DTO.UserInsertDTO;
import com.queiroz.HappyPet.entities.DTO.UserUpdateDTO;
import com.queiroz.HappyPet.entities.User;
import com.queiroz.HappyPet.repositories.RoleRepository;
import com.queiroz.HappyPet.repositories.UserRepository;
import com.queiroz.HappyPet.service.exceptions.DatabaseException;
import com.queiroz.HappyPet.service.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> page = repository.findAll(pageable);
        return page.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findByCode(Long id) {
        Optional<User> user = repository.findById(id);
        User obj = user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserDTO(obj);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO obj) {
        User user = new User();
        fromDTO(user, obj);
        user.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        user = repository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO obj) {
        try {
            User user = repository.getById(id);
            fromDTO(user, obj);
            repository.save(user);
            return new UserDTO(user);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
            repository.deleteById(id);
    }


    public void fromDTO(User user, UserDTO obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.getRoles().clear();
        for (RoleDTO roleDTO : obj.getRoles()) {
            user.getRoles().add(roleRepository.getById(roleDTO.getId()));
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            log.error("Usuário " + username + " não encontrado");
            throw new UsernameNotFoundException("Email não encotrado");
        }
        log.info("Usuário " + username + " encontrado");
        return user;
    }
}
