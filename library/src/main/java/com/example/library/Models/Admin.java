package com.example.library.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties({"admin","author","book","student","transaction"})
    private List<Request> requestsToProcess;

    @CreationTimestamp
    private Date createdOn;


}
