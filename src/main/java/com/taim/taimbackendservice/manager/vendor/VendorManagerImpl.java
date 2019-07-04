package com.taim.taimbackendservice.manager.vendor;

import com.taim.taimbackendservice.mapper.CreateVendorDTOMapper;
import com.taim.taimbackendservice.mapper.VendorDTOMapper;
import com.taim.taimbackendservice.model.Vendor;
import com.taim.taimbackendservice.repository.VendorRepository;
import com.taim.taimbackendservicemodel.CreateVendorDTO;
import com.taim.taimbackendservicemodel.VendorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorManagerImpl implements VendorManager {

    private final VendorRepository vendorRepository;
    private final CreateVendorDTOMapper createVendorDTOMapper;
    private final VendorDTOMapper vendorDTOMapper;

    @Autowired
    public VendorManagerImpl(VendorRepository vendorRepository,
                             CreateVendorDTOMapper createVendorDTOMapper,
                             VendorDTOMapper vendorDTOMapper) {
        this.vendorRepository = vendorRepository;
        this.createVendorDTOMapper = createVendorDTOMapper;
        this.vendorDTOMapper = vendorDTOMapper;
    }

    @Override
    @Transactional
    public List<VendorDTO> getAllVendors() {
        return this.vendorRepository.findAll().stream()
                .map(this.vendorDTOMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveVendor(CreateVendorDTO createVendorDTO) {
        Vendor vendor = this.createVendorDTOMapper.map(createVendorDTO);
        this.vendorRepository.saveAndFlush(vendor);
    }
}
