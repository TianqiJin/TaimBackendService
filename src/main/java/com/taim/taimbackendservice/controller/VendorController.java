package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.manager.vendor.VendorManager;
import com.taim.taimbackendservicemodel.CreateVendorDTO;
import com.taim.taimbackendservicemodel.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendorController {

    private final VendorManager vendorManager;

    @Autowired
    public VendorController(VendorManager vendorManager) {
        this.vendorManager = vendorManager;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/vendors",
            params = "action=getAll"
    )
    public List<VendorDTO> getAllVendors() {
        return this.vendorManager.getAllVendors();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/vendors",
            params = "action=save"
    )
    public void saveVendor(@RequestBody CreateVendorDTO createVendorDTO) {
        this.vendorManager.saveVendor(createVendorDTO);
    }
}
