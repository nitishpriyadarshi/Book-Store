package com.bridgelabz.bookstoreapp.entity;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="UserDetails")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userID;
    private  String fullName;
    private String email;
    private String phoneNumber;
    private String password;

    public User(UserDTO dto) {
        this.fullName = dto.getFullName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.password = dto.getPassword();

    }
    public User(Integer userID,UserDTO dto) {
        this.userID=userID;
        this.fullName = dto.getFullName();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.password = dto.getPassword();

    }

}
