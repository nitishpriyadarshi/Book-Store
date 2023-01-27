package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.Book;

import java.util.List;

public interface IBookService {
    public Book insertBook(BookDTO bookdto);

    public List<Book> getAllBookRecords();

    public List<Book> getBookRecord(Integer id);

    public Book updateBookRecord(Integer id, BookDTO dto);

    public List<Book> getRecordByBookName(String bookName);

    public Book deleteBookRecord(Integer id);
    public List<Book> sortRecordDesc();

    public List<Book> sortRecordAsc();

    public Book updateQuantity(Integer id, Integer quantity);
}
