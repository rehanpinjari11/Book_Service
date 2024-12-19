package com.example.demo.services;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements IBook {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Long id, Book updateBook){
        return bookRepository.findById(id).map(existingBook-> {
            existingBook.setTitle(updateBook.getTitle());
            existingBook.setAuthor(updateBook.getAuthor());
            return bookRepository.save(existingBook);
        }).orElse(null);

    }

    @Override
    public boolean deleteBook(Long id){
        if (bookRepository.existsById(id)){
             bookRepository.deleteById(id);
             return true;
        }
        return false;
    }

    public Book saveOrUpdateBookByName(Book book){
        Optional<Book> existingBook = bookRepository.findByTitle(book.getTitle());

        if(existingBook.isPresent()){
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setAuthor(book.getAuthor());
            return bookRepository.save(bookToUpdate);
        }
        else {
            return bookRepository.save(book);
        }
    }
}
