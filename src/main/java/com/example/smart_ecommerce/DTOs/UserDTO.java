package com.example.smart_ecommerce.DTOs;

import com.example.smart_ecommerce.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


public class UserDTO {

    @Data
    public static class UserRegistrationDTO {
        @NotBlank(message = "Name is required")
        private String firstName;

        @NotBlank(message = "Name is required")
        private String lastName;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message="Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
        )              
        
        private String password;

        private Role role;
    }
}
