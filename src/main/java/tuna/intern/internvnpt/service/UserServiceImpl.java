package tuna.intern.internvnpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuna.intern.internvnpt.domain.UserEntity;
import tuna.intern.internvnpt.dto.LoginDto;
import tuna.intern.internvnpt.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        return user != null ? "Valid user!" : "Invalid user!";
    }

    @Override
    public UserEntity updateUser(Integer id, UserEntity user) {
        if (userRepository.findById(id).isPresent()){
            UserEntity existUser = userRepository.findById(id).get();
            existUser.setUsername(user.getUsername());
            existUser.setEmail(user.getEmail());
            UserEntity updateUser = userRepository.save(existUser);
            return new UserEntity(updateUser.getId(), updateUser.getUsername(),
                    updateUser.getEmail(), updateUser.getPassword());
        }else{
            return null;
        }
    }


}
