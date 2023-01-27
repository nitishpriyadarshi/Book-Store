package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ChangePasswordDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.entity.User;

import java.util.List;

public interface IUserService {
    public String registerUser(UserDTO userdto);

    public User userLogin(LoginDTO logindto);

    public List<User> getAllRecords();

    public User getRecord(Integer id);

    public User getRecordByToken(String token);

    public User updateRecord(Integer id, UserDTO dto);
    public User changePassword(ChangePasswordDTO dto);
    public User getUserByEmailId(String email);
    public String deleteUserByToken(String token);
}
