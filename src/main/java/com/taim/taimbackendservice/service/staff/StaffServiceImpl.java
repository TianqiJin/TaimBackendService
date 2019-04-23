package com.taim.taimbackendservice.service.staff;

import com.taim.taimbackendservice.model.Staff;
import com.taim.taimbackendservice.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements IStaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getById(long id) {
        return staffRepository.getOne(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.saveAndFlush(staff);
    }

    @Override
    public Staff update(Staff staff) {
        return staffRepository.saveAndFlush(staff);
    }
}
