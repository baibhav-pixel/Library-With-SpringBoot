package com.example.library.Requests;

import com.example.library.Models.Author;
import com.example.library.Models.Book;
import com.example.library.Models.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    @NotNull
    private String bookName;

    @NotNull
    private String authorName;

    @NotNull
    @Email
    private String authorEmail;

    @NotNull
    private String genre;

    //first build book
    public Book to(){
        return Book.builder()
                .name(this.getBookName())
                .genre(Genre.valueOf(genre))
                .build();
    }

    //build author
    public Author toAuthor() {
        return Author.builder()
                .name(authorName)
                .email(authorEmail)
                .build();
    }

    //connect book and author
    public Book to(Author author)
    {
        return Book.builder()
                .name(bookName)
                .genre(Genre.valueOf(genre))
                .author(author)
                .build();
    }
}
