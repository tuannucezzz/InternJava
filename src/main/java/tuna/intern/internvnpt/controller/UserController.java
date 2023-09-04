package tuna.intern.internvnpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tuna.intern.internvnpt.annotation.Log;
import tuna.intern.internvnpt.domain.UserEntity;
import tuna.intern.internvnpt.dto.LoginDto;
import tuna.intern.internvnpt.repository.UserRepository;
import tuna.intern.internvnpt.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService useService;
    @Autowired
    private UserRepository userRepository;

    @Log
    @GetMapping("hello")
    public ResponseEntity<String> ping(@RequestParam String name) {
        return ResponseEntity.ok(String.format("Hello %s", name));
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> addUser(@Valid @RequestBody UserEntity user) {
        UserEntity userResponse = useService.createUser(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDto loginDto) {
        String validUser = useService.loginUser(loginDto);
        return new ResponseEntity<>(validUser, HttpStatus.OK);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserEntity>> allUser() {
        List<UserEntity> user = userRepository.listUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @Cacheable(cacheNames = "cacheStore", key = "#id")
    public UserEntity getUserById(@PathVariable Integer id) {
        UserEntity user = userRepository.findUserById(id);
        return user;
    }

    @PutMapping(value = "/user/{id}")
    @CachePut(cacheNames = "cacheStore", key = "#user.id")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity updateUser(@PathVariable(value = "id") Integer id,
                                                         @RequestBody UserEntity user){
        return useService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    @CacheEvict(cacheNames = "cacheStore", key = "#id")
    public UserEntity deleteUserById(@PathVariable Integer id) {
        UserEntity user = new UserEntity();
        userRepository.deleteById(id);
        return user;
    }
}
