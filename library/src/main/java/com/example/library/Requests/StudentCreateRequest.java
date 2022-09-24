package com.example.library.Requests;

import com.example.library.Models.Student;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotNull
    private String rollNo;

    @NotNull
    private String name;

    @Min(16)
    private Integer age;

    public Student to()
    {
        return Student.builder()
                .rollNo(rollNo)
                .name(name)
                .age(age)
                .build();
    }
}
