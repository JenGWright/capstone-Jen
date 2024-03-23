package org.example.Controller;


import org.example.Service.LeaveService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeLeaveController {

    LeaveService leaveService;

    public EmployeeLeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
}
