package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Response;
import com.example.demo.services.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")

public class BookController {

    @Autowired
    BookServiceImp bookServiceImp;

//    public BookController(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<Response> getAllBooks() {

        List<Book> allBooks = bookServiceImp.getAllBooks();
        Response responseData = new Response("Fetch The Data Successfully", allBooks);
        return new ResponseEntity<Response> (responseData, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return bookServiceImp.addBook(book);
    }

   @GetMapping("/getBookById/{id}")
    public ResponseEntity<Response>getBookById(@PathVariable Long id){
        Optional<Book>book=bookServiceImp.getBookById(id);
        if(book.isPresent()){
            Response response = new Response("Book Found",book);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else{
            Response response = new Response("Not found",null);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
   }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Response> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookServiceImp.updateBook(id, updatedBook);
        if (book != null) {
            Response responseData = new Response("Book updated successfully", book);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            Response responseData = new Response("Book not found", null);
            return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Response>deleteBook(@PathVariable Long id){
        if (bookServiceImp.deleteBook(id)){
            Response response = new Response("Book Deleted Successfully",null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else {
            Response response=new Response("Book not found",null);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/saveOrUpdateBook")
    public ResponseEntity<Response> saveOrUpdateBook(@RequestBody Book book) {
        Book savedBook = bookServiceImp.saveOrUpdateBookByName(book);
        Response response;
        if (savedBook.getId() != null) {
             response = new Response("Book Updated Successfully", book);
        } else {
             response = new Response("New Book Created Successfully", null);

        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
