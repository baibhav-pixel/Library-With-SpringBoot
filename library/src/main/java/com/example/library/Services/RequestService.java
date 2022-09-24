package com.example.library.Services;

import com.example.library.Models.Request;
import com.example.library.Repositories.RequestRepository;
import com.example.library.Requests.PlaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request getRequestById(Integer id){
        return requestRepository.findById(id).orElse(null);
    }

    public Request saveOrUpdateRequest(Request request){
        return requestRepository.save(request);
    }
}
