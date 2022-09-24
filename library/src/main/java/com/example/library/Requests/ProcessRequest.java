package com.example.library.Requests;

import com.example.library.Models.Request;
import com.example.library.Models.Transaction;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessRequest {

    @NotNull
    private Integer requestId;

    @NotNull
    private Integer adminId;

    @NotNull
    private String requestStatus;

    private String adminComment;


}
