package com.example.smart_ecommerce.DTOs;

import com.example.smart_ecommerce.entities.Role;
import com.example.smart_ecommerce.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;


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

        private Role role = Role.CUSTOMER;
    }

    @Data
    public static class UserResponseDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private Role role;
        private Date createdAt;
        private Date updatedAt;

        public static UserResponseDTO fromEntity(User user) {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            return dto;
        }
    }

    @Data
    public static class UpdateUserRequestDTO {
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
        private String firstName;
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
        private String lastName;
        @Email(message = "Invalid email format")
        private String email;
    }
}
