package com.example.smart_ecommerce.services;


import com.example.smart_ecommerce.DTOs.UserDTO;
import com.example.smart_ecommerce.entities.User;
import com.example.smart_ecommerce.global.exceptions.ResourceNotFound;
import com.example.smart_ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User addUser(UserDTO.UserRegistrationDTO userDto){
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with email " + userDto.getEmail() + " already exists");
        }
        User user = modelMapper.map(userDto, User.class);

        return userRepository.save(user);
    }

    public boolean deleteUser(Long UserId){
        Optional<User> existingUser= userRepository.findById(UserId);
        if(existingUser.isPresent()){
            userRepository.deleteById(UserId);
            return true;
        } else {
            throw new ResourceNotFound("User with id " + UserId + " not found");
        }

    }
}
