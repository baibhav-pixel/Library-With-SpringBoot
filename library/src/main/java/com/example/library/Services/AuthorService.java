package com.example.library.Services;

import com.example.library.Models.Author;
import com.example.library.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author createOrGetAuthor(Author author){
        Author retrievedAuthorFromDB = getAuthorByEmail(author.getEmail());

        if(retrievedAuthorFromDB != null){
            return retrievedAuthorFromDB;
        }

        return authorRepository.save(author);
    }

    private Author getAuthorByEmail(String email) {
        return authorRepository.findByEmailId(email);
    }
}
