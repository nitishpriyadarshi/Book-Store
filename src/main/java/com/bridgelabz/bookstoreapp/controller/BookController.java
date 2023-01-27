package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.Book;
import com.bridgelabz.bookstoreapp.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller class to make api calls
@CrossOrigin
@RestController
@RequestMapping("/bookdetails")
public class BookController {
    @Autowired
    private IBookService bookService;

    //Ability to call api to insert Book record
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO bookdto) {
        ResponseDTO dto = new ResponseDTO("Book registered successfully !", bookService.insertBook(bookdto));
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }

    //Ability to call api to retrieve all book records
    @GetMapping("/retrieveAllBooks")
    public ResponseEntity<ResponseDTO> getAllBookRecords() {
        List<Book> newBook = bookService.getAllBookRecords();
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //Ability to call api to retrieve record by book name
    @GetMapping("/retrieve/{bookName}")
    public ResponseEntity<ResponseDTO> getRecordByBookName(@PathVariable String bookName){
        List<Book> newBook = bookService.getRecordByBookName(bookName);
        ResponseDTO dto = new ResponseDTO("Record for particular book retrieved successfully !",newBook);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    //Ability to call api to get record by id
    @GetMapping("/retrieveBook/{id}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id) {
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", bookService.getBookRecord(id));
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //Ability to call api to update book record by id
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id, @Valid @RequestBody BookDTO bookdto) {
        ResponseDTO dto = new ResponseDTO("Record updated successfully !", bookService.updateBookRecord(id, bookdto));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    //Ability to call api to delete book record by id
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> deleteBookRecord(@PathVariable Integer id) {
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", bookService.deleteBookRecord(id));
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    //Ability to call api to sort book records in ascending order
    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDTO> sortRecordAsc() {
        List<Book> newBook = bookService.sortRecordAsc();
        ResponseDTO dto = new ResponseDTO("Records for book sorted in ascending order successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //Ability to call api to sort book records in descending order
    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDTO> sortRecordDesc() {
        List<Book> newBook = bookService.sortRecordDesc();
        ResponseDTO dto = new ResponseDTO("Records for book sorted in descending order successfully !", newBook);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    ///Ability to call api to update quantity of books by id
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id,@RequestParam Integer quantity){
        Book newBook = bookService.updateQuantity(id,quantity);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",newBook);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
}
