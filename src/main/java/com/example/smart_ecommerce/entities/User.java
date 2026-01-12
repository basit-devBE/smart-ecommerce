package com.example.smart_ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(name= "uk_user_email" , columnNames = {"email"}),
},
        indexes = {
                @Index(name = "idx_user_email", columnList = "email"),
                @Index(name = "idx_user_id", columnList = "id")
        }

)
public class User {
    @Setter
    @Getter
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Setter
    @Getter
    @Column(nullable = false)
    private String firstName;
    @Setter
    @Getter
    @Column(nullable = false)
    private String lastName;
    @Setter
    @Getter
    @Column(nullable = false)
    private String email;
    @Setter
    @Getter
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.CUSTOMER;

    public  User(){};

    public User(Long id, String firstName, String lastName, String email, Role role, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

}


