package com.bridgelabz.bookstoreapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
    @NotEmpty(message="Full name cant be empty")
    @Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="FullName is Invalid")
    private  String fullName;



    @NotEmpty(message="Email cant be empty")
    @Pattern(regexp = "^[\\w+-]+(\\.[\\w-]+)*@[^_\\W]+(\\.[^_\\W]+)?(?=(\\.[^_\\W]{3,}$|\\.[a-zA-Z]{2}$)).*$", message = "Please enter a valid email id")
    private String email;

    @NotEmpty(message="PhoneNumber cant be empty")
    private String phoneNumber;

    @NotBlank(message="Password cant be blank")
    private String password;

}
