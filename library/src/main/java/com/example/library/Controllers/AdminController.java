package com.example.library.Controllers;

import com.example.library.Models.Admin;
import com.example.library.Requests.AdminCreateRequest;
import com.example.library.Requests.ProcessRequest;
import com.example.library.Response.ProcessResponse;
import com.example.library.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public Admin createAdmin(@Valid @RequestBody AdminCreateRequest adminCreateRequest)
    {
        return adminService.createAdmin(adminCreateRequest);
    }

    @GetMapping("/admin")
    public List<Admin> getAdmins()
    {
        return adminService.getAdmins();
    }

    @PostMapping("/admin/process")
    public ProcessResponse processRequest(@Valid @RequestBody ProcessRequest processRequest) throws Exception {
        return adminService.processRequest(processRequest);
    }
}
