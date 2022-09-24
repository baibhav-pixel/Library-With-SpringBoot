package com.example.library.Services;

import com.example.library.Models.*;
import com.example.library.Repositories.AdminRepository;
import com.example.library.Requests.AdminCreateRequest;
import com.example.library.Requests.ProcessRequest;
import com.example.library.Response.ProcessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RequestService requestService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionService transactionService;

    public Admin createAdmin(AdminCreateRequest adminCreateRequest) {
        return adminRepository.save(adminCreateRequest.to());
    }

    public List<Admin> getAdmins()
    {
        return adminRepository.findAll();
    }

    public ProcessResponse processRequest(ProcessRequest processRequest) throws Exception {
        //check for valid admin, check for book availability

        Request request = requestService.getRequestById(processRequest.getRequestId());
        Book book = bookService.getBookById(request.getBook().getId());

        if(request==null)
        {
            throw new Exception("Request does not exists");
        }

        if(request.getAdmin().getId()!=processRequest.getAdminId() || request.getAdmin()==null)
        {
            throw new Exception("This admin is not assigned to the request");
        }

        if(book.getStudent() != null && request.getRequestType()==RequestType.ISSUE)
        {
            throw new Exception("Can't issue this book as it is already issued by someone");
        }

        if (!RequestStatus.PENDING.equals(request.getRequestStatus())) {
            throw new Exception("Request is already processed");
        }

        //Admin work for accept/reject

        if(RequestStatus.REJECTED.equals(processRequest.getRequestStatus()))
        {
            request.setRequestStatus(RequestStatus.REJECTED);
            request.setAdminComment(processRequest.getAdminComment());

            //store request stats in DB
            requestService.saveOrUpdateRequest(request);

            return ProcessResponse.builder()
                    .requestStatus(RequestStatus.REJECTED)
                    .adminComment(processRequest.getAdminComment())
                    .build();
        }

        request.setRequestStatus(RequestStatus.APPROVED);
        request.setAdminComment(processRequest.getAdminComment());

        requestService.saveOrUpdateRequest(request);
        transactionService.createTransaction(request);

        return ProcessResponse.builder()
                .requestStatus(RequestStatus.APPROVED)
                .adminComment(processRequest.getAdminComment())
                .build();
    }
}
