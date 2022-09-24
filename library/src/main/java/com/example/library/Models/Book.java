package com.example.library.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @JoinColumn
    @ManyToOne // Many (Books)  to   One (Author)
    @JsonIgnoreProperties({"bookList"})
    private Author author;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"book"})
    private Student student;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties({"book","student","transaction","admin"})
    private List<Request> requestList;

    @CreationTimestamp
    private Date createdOn;
}
