package tuna.intern.internvnpt.service;

import org.springframework.stereotype.Service;
import tuna.intern.internvnpt.domain.UserEntity;
import tuna.intern.internvnpt.dto.LoginDto;

@Service
public interface UserService {
    public UserEntity createUser(UserEntity user);

    public String loginUser(LoginDto loginDto);

    public UserEntity updateUser(Integer id, UserEntity user);
}
