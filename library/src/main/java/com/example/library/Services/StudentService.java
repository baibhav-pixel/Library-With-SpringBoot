package com.example.library.Services;

import com.example.library.Models.*;
import com.example.library.Repositories.StudentRepository;
import com.example.library.Requests.PlaceRequest;
import com.example.library.Requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AdminService adminService;

    @Autowired
    RequestService requestService;

    @Autowired
    BookService bookService;

    public Student createStudent(StudentCreateRequest studentCreateRequest) {
        return studentRepository.save(studentCreateRequest.to());
    }

    public String placeRequest(PlaceRequest placeRequest) throws Exception {

        List<Admin> adminList = adminService.getAdmins();
        Book book = bookService.getBookById(placeRequest.getBookId());

        Admin admin = adminList.stream()
                .min(Comparator.comparingInt(a -> a.getRequestsToProcess().size()))
                .orElse(null);

        //If someone returns an already returned book and admin approves then throw error
        if(placeRequest.toRequest().getRequestType() == RequestType.RETURN && book.getStudent()==null
        && placeRequest.toRequest().getRequestStatus()== RequestStatus.PENDING)
        {
            placeRequest.toRequest().setRequestStatus(RequestStatus.REJECTED);
            throw new Exception("Can't return a book which is not issued");
        }

        return requestService.saveOrUpdateRequest(placeRequest.toRequest(admin)).getRequestId();
    }
}
