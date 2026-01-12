package com.example.smart_ecommerce.services;


import com.example.smart_ecommerce.DTOs.UserDTO;
import com.example.smart_ecommerce.entities.User;
import com.example.smart_ecommerce.global.exceptions.EmailAlreadyExist;
import com.example.smart_ecommerce.global.exceptions.ResourceNotFound;
import com.example.smart_ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;

    }
    public UserDTO.UserResponseDTO addUser(UserDTO.UserRegistrationDTO user){
        Optional<User> existinguser = userRepository.findByEmail(user.getEmail());
        if(existinguser.isPresent()){
            throw new EmailAlreadyExist("Email Already Exists");
        }
        //TODO:Use a mapstruct for this
        User newuser = new User();
        newuser.setFirstName(user.getFirstName());
        newuser.setLastName(user.getLastName());
        newuser.setPassword(user.getPassword());
        newuser.setEmail(user.getEmail());
        User createduser = userRepository.save(newuser);
        return UserDTO.UserResponseDTO.fromEntity(createduser);
    }

    public Optional<UserDTO.UserResponseDTO> findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .map(UserDTO.UserResponseDTO::fromEntity);
    }

    public Optional<UserDTO.UserResponseDTO> findUserById(Long userId){
        return userRepository.findById(userId).map(UserDTO.UserResponseDTO::fromEntity);
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

    public List<UserDTO.UserResponseDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(UserDTO.UserResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDTO.UserResponseDTO updateUser(Long UserId, UserDTO.UpdateUserRequestDTO userDto){
        Optional<User> foundUser = userRepository.findById(UserId);
        if(foundUser.isPresent()){
            User user = foundUser.get();
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            User updatedUser = userRepository.save(user);
            return UserDTO.UserResponseDTO.fromEntity(updatedUser);
        }else{
            throw new ResourceNotFound("User not found");
        }
    }
}
