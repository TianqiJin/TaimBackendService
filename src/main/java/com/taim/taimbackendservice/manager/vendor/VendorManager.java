package com.taim.taimbackendservice.manager.vendor;

import com.taim.taimbackendservicemodel.CreateVendorDTO;
import com.taim.taimbackendservicemodel.VendorDTO;

import java.util.List;

public interface VendorManager {
    List<VendorDTO> getAllVendors();
    void saveVendor(CreateVendorDTO createVendorDTO);
}
