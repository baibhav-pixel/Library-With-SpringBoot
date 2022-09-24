package com.example.library.Services;

import com.example.library.Models.Author;
import com.example.library.Models.Book;
import com.example.library.Repositories.BookRepository;
import com.example.library.Requests.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public Book createBook(BookCreateRequest bookCreateRequest)
    {
        Author author = bookCreateRequest.toAuthor(); //create author
        author = authorService.createOrGetAuthor(author); //store the author in authorDB and get authorId
        return bookRepository.save(bookCreateRequest.to(author)); //attach author in the book and store in book table
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book createOrUpdateBook(Book book) {
        return bookRepository.save(book);
    }
}
