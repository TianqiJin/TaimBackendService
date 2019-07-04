package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.manager.staff.StaffManager;
import com.taim.taimbackendservice.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StaffController {

    private final StaffManager staffService;

    @Autowired
    public StaffController(StaffManager staffService) {
        this.staffService = staffService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/staffs",
            params = "action=getAll"
    )
    public List<Staff> getCustomerOverview() {
        return staffService.getAll();
    }

}
