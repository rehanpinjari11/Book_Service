package com.example.demo.services;

import com.example.demo.entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBook {

    List<Book> getAllBooks();
    Book addBook(Book book);
    Optional<Book> getBookById(Long id);
    Book updateBook(Long id, Book updatebook);
    boolean deleteBook(Long id);
}
