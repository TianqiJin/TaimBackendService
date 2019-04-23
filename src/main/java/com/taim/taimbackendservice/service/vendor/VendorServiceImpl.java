package com.taim.taimbackendservice.service.vendor;

import com.taim.taimbackendservice.model.Vendor;
import com.taim.taimbackendservice.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements IVendorService{

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    @Override
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getById(long id) {
        return vendorRepository.getOne(id);
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorRepository.saveAndFlush(vendor);
    }

    @Override
    public Vendor update(Vendor vendor) {
        return vendorRepository.saveAndFlush(vendor);
    }
}
