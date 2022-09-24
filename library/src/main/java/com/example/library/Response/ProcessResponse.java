package com.example.library.Response;

import com.example.library.Models.RequestStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessResponse {
    private RequestStatus requestStatus;
    private String adminComment;
}
