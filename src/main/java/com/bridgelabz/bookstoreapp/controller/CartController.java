package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Cart;
import com.bridgelabz.bookstoreapp.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cartdetails")
public class CartController {
    //Autowired ICartService to inject its dependency here
    @Autowired
    ICartService cartService;

    //Ability to call api to insert all cart records
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody CartDTO cartdto){
        ResponseDTO dto = new ResponseDTO("Book Added To Cart successfully !",cartService.insertCart(cartdto));
        System.out.println("Cart record updated successfully  "+ dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //Ability to call api to retrieve all card records
    @GetMapping("/retrieveAllCarts")
    public ResponseEntity<ResponseDTO> getAllCartRecords(){
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !",cartService.getAllCartRecords());
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    //Ability to call api to retrieve cart record by id
    @GetMapping("/retrieveCart/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id){
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",cartService.getCartRecord(id));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    //Ability to call api to update cart by id
    @PutMapping("/updateCart/{id}")
    public ResponseEntity<ResponseDTO> updateCartRecord(@PathVariable Integer id,@Valid @RequestBody CartDTO cartdto){
        ResponseDTO dto = new ResponseDTO("Record updated successfully !",cartService.updateCartRecord(id,cartdto));
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    //Ability to call api to update quantity of book in cart by id
    @PutMapping("/updateQuantity/{id}/{quantity}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id,@PathVariable Integer quantity){
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",cartService.updateQuantity(id,quantity));
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    //Ability to call api to delete cart by id
    @DeleteMapping("/deleteCart/{id}")
    public ResponseEntity<ResponseDTO> deleteCartRecord(@PathVariable Integer id) {
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", cartService.deleteCartRecord(id));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
    //Ability to call api to delete All cart
    @DeleteMapping("/deleteall")
    public ResponseEntity<ResponseDTO> deleteBooks() {
        List<Cart> books = cartService.deleteAllFromCart();
        return new ResponseEntity(books, HttpStatus.OK);
    }
    @PutMapping("/updateCartbyuserid/{id}/{quantity}")
    public ResponseEntity<ResponseDTO> updateCartRecordByUserID(@PathVariable Integer id,@PathVariable Integer quantity){
        ResponseDTO dto = new ResponseDTO("Record updated successfully By using UserID!",cartService.updateCartRecordByUserID(id,quantity));
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }


}
