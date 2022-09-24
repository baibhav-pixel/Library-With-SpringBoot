package com.example.library.Controllers;

import com.example.library.Models.Book;
import com.example.library.Requests.BookCreateRequest;
import com.example.library.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public Book createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest)
    {
        return bookService.createBook(bookCreateRequest);
    }
}
