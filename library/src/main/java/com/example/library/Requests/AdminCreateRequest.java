package com.example.library.Requests;

import com.example.library.Models.Admin;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateRequest {
    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    public Admin to()
    {
        return Admin.builder()
                .name(name)
                .email(email)
                .build();
    }
}
