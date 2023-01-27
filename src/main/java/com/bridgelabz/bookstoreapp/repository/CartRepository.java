package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value="select * from cart where bookid =:bookId",nativeQuery =true)
    List<Cart> findByBookId(Integer bookId);

    @Query(value="select * from cart where userid =:userId",nativeQuery =true)
    List<Cart> findByUserId(Integer userId);

    @Query(value="select * from cart where userid =:userId and bookid =:bookId",nativeQuery =true)
    List<Cart> findByUserAndBookId(Integer userId, Integer bookId);

    @Query(value="select * from cart where userid =:userID",nativeQuery =true)
    List<Cart> findByUserID(Integer userID);



}
