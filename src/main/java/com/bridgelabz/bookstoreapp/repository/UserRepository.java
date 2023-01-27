package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value="select * from user_details where email =:email",nativeQuery = true)
    public Optional<User> findByEmail(String email);

    @Query(value="delete from user_details where email =:email",nativeQuery = true)
    void deleteByEmail(String email);
}
