package com.taim.taimbackendservice.controller;

import com.taim.taimbackendservice.mapper.CreateVendorDTOMapper;
import com.taim.taimbackendservice.mapper.VendorDTOMapper;
import com.taim.taimbackendservice.model.Vendor;
import com.taim.taimbackendservice.service.vendor.IVendorService;
import com.taim.taimbackendservicemodel.CreateVendorDTO;
import com.taim.taimbackendservicemodel.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VendorController {

    private final IVendorService vendorService;
    private final VendorDTOMapper vendorDTOMapper;
    private final CreateVendorDTOMapper createVendorDTOMapper;

    @Autowired
    public VendorController(IVendorService vendorService,
                            VendorDTOMapper vendorDTOMapper,
                            CreateVendorDTOMapper createVendorDTOMapper) {
        this.vendorService = vendorService;
        this.vendorDTOMapper = vendorDTOMapper;
        this.createVendorDTOMapper = createVendorDTOMapper;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/vendors",
            params = "action=getAll"
    )
    public List<VendorDTO> getAllVendors() {
        return this.vendorService.getAll().stream()
                .map(this.vendorDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/vendors",
            params = "action=save"
    )
    public Vendor saveVendor(@RequestBody CreateVendorDTO createVendorDTO) {
        return vendorService.save(this.createVendorDTOMapper.reverse().convert(createVendorDTO));
    }
}
