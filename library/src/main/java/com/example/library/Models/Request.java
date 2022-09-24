package com.example.library.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String requestId;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

    private String adminComment;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"requestList","author","student"})
    private Book book;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"requestList"})
    private Student student;

    @OneToOne(mappedBy = "request")
    @JsonIgnoreProperties({"request"})
    private Transaction transaction;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("requestsToProcess")
    private Admin admin;
}
