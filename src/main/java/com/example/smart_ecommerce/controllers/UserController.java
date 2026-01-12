package com.example.smart_ecommerce.controllers;


import com.example.smart_ecommerce.DTOs.UserDTO;
import com.example.smart_ecommerce.entities.User;
import com.example.smart_ecommerce.global.Response.Result;
import com.example.smart_ecommerce.global.exceptions.ResourceNotFound;
import com.example.smart_ecommerce.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/find/{emailAddress}")
    public ResponseEntity<Result<UserDTO.UserResponseDTO>> findUserByEmail(@PathVariable("emailAddress") String email){
        UserDTO.UserResponseDTO user = userService.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("User with email " + email + " not found"));
        
        Result<UserDTO.UserResponseDTO> result = new Result<>();
        result.setData(user);
        result.setSuccess(true);
        result.setMessage("User found successfully");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public  ResponseEntity<Result<UserDTO.UserResponseDTO>> createUser (@Valid @RequestBody UserDTO.UserRegistrationDTO request){
        UserDTO.UserResponseDTO newUser = userService.addUser(request);
        Result<UserDTO.UserResponseDTO> result = new Result<>();
        result.setData(newUser);
        result.setSuccess(true);
        result.setMessage("User created successfully");
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result<Boolean>> deleteUser (@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        Result<Boolean> result = new Result<>();
        result.setData(deleted);
        result.setSuccess(true);
        result.setMessage("User deleted successfully");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Result<UserDTO.UserResponseDTO>> findUserById(@PathVariable Long id){
        UserDTO.UserResponseDTO user = userService.findUserById(id)
                .orElseThrow(()-> new ResourceNotFound("User not found"));
        Result<UserDTO.UserResponseDTO> result = new Result<>();
        result.setData(user);
        result.setMessage("User fetched successfully");
        result.setSuccess(true);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<Result<List<UserDTO.UserResponseDTO>>> getAllUsers(){
        List<UserDTO.UserResponseDTO> users = userService.getAllUsers();
        Result<List<UserDTO.UserResponseDTO>> result = new Result<>();
        result.setData(users);
        result.setSuccess(true);
        result.setMessage("Users retrieved successfully");
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<Result<UserDTO.UserResponseDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO.UpdateUserRequestDTO request){
        UserDTO.UserResponseDTO updatedUser = userService.updateUser(id,request);
        Result<UserDTO.UserResponseDTO> result = new Result<>();
        result.setData(updatedUser);
        result.setSuccess(true);
        result.setMessage("User updated successfully");
        return ResponseEntity.ok(result);
    }

}
