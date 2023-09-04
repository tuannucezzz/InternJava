package tuna.intern.internvnpt.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import tuna.intern.internvnpt.domain.UserEntity;
import tuna.intern.internvnpt.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ValidUserValidator implements ConstraintValidator<ValidUser, String> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Optional<UserEntity> user = userRepository.findByEmail(value);
        if (value != null && user.isPresent()) return true;
        return false;
    }
}
