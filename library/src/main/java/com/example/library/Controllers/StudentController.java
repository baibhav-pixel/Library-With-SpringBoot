package com.example.library.Controllers;

import com.example.library.Models.Student;
import com.example.library.Requests.PlaceRequest;
import com.example.library.Requests.StudentCreateRequest;
import com.example.library.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        return studentService.createStudent(studentCreateRequest);
    }

    @PostMapping("/student/request")
    public String placeRequest(@RequestBody PlaceRequest placeRequest) throws Exception {
        return studentService.placeRequest(placeRequest);
    }


}
