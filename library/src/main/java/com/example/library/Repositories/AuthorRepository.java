package com.example.library.Repositories;

import com.example.library.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query("select a from Author a where a.email = :email")
    Author findByEmailId( String email);
}
