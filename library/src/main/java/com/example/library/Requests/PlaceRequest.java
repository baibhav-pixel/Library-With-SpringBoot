package com.example.library.Requests;

import com.example.library.Models.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceRequest {

    @NotNull
    private String requestType;

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer studentId;


    public Request toRequest(){
        return Request.builder()
                .book(this.bookId == null ? null : Book.builder().id(this.bookId).build())
                .student(this.studentId == null ? null : Student.builder().id(this.studentId).build())
                .requestType(RequestType.valueOf(this.requestType))
                .requestStatus(RequestStatus.PENDING)
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    public Request toRequest(Admin admin)
    {
        return Request.builder()
                .admin(admin)
                .book(this.bookId == null ? null : Book.builder().id(this.bookId).build())
                .student(this.studentId == null ? null : Student.builder().id(this.studentId).build())
                .requestType(RequestType.valueOf(this.requestType))
                .requestStatus(RequestStatus.PENDING)
                .requestId(UUID.randomUUID().toString())
                .build();
    }
}
