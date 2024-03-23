package org.example.Service;

import org.example.Entity.EmployeeLeave;
import org.example.Repository.LeaveManageSQLRepo;
import org.example.Repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service(value = "leaveService")

public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveManageSQLRepo leaveManageSQLRepo;


    @Autowired
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public void applyLeave(EmployeeLeave employeeLeave) {
        // Convert java.util.Date to Calendar
        Calendar fromDate = Calendar.getInstance();
        fromDate.setTime(employeeLeave.getFromDate());

        Calendar toDate = Calendar.getInstance();
        toDate.setTime(employeeLeave.getToDate());

        // Calculate duration (inclusive of both start and end dates)
        long durationInMillis = toDate.getTimeInMillis() - fromDate.getTimeInMillis();
        int durationInDays = (int) (durationInMillis / (24 * 60 * 60 * 1000)) + 1;

        // Set duration and other properties
        employeeLeave.setDuration(durationInDays);
        employeeLeave.setActive(true);

        // Save to repository
        leaveRepository.save(employeeLeave);
    }


    public List<EmployeeLeave> getAllLeaves(){
        return leaveRepository.findAll();
    }

    public void updateEmployeeLeave(EmployeeLeave employeeLeave) {
        leaveRepository.save(employeeLeave);
    }

    public List<EmployeeLeave> getAllActiveLeaves(){
        return leaveRepository.getAllActiveLeaves();
    }

    public List<EmployeeLeave> getAllLeavesOfUser (String name) {
        return leaveRepository.getAllLeavesOfUser (name);
    }

    public List<EmployeeLeave> getAllLeavesOnStatus(boolean pending, boolean accepted, boolean rejected) {

        StringBuffer whereQuery = new StringBuffer();
        if (pending)
            whereQuery.append("active=true or ");
        if (accepted)
            whereQuery.append("(active=false and accept_reject_flag=true) or ");
        if (rejected)
            whereQuery.append("(active=false and accept_reject_flag=false) or ");

        whereQuery.append(" 1=0");

        return leaveManageSQLRepo.getAllLeavesOnStatus(whereQuery);

    }
}
