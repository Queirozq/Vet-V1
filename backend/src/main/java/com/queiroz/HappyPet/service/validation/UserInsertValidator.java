package com.queiroz.HappyPet.service.validation;

import com.queiroz.HappyPet.entities.DTO.UserInsertDTO;
import com.queiroz.HappyPet.entities.User;
import com.queiroz.HappyPet.repositories.UserRepository;
import com.queiroz.HappyPet.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;


    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(userInsertDTO.getEmail());
        if(user != null){
           list.add(new FieldMessage("Email", "Email já existe"));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
