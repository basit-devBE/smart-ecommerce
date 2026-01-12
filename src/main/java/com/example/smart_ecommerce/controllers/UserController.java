package com.example.smart_ecommerce.controllers;


import com.example.smart_ecommerce.DTOs.UserDTO;
import com.example.smart_ecommerce.entities.User;
import com.example.smart_ecommerce.global.Response.Result;
import com.example.smart_ecommerce.global.exceptions.ResourceNotFound;
import com.example.smart_ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    


    @GetMapping("/find/{email}")
    public ResponseEntity<Result<User>> findUserByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("User with email " + email + " not found"));
        
        Result<User> result = new Result<>();
        result.setData(user);
        result.setSuccess(true);
        result.setMessage("User found successfully");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public  ResponseEntity<Result<User>> createUser (@Valid @RequestBody UserDTO.UserRegistrationDTO request){
        User newUser = userService.addUser(request);
        Result<User> result = new Result<>();
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

}
