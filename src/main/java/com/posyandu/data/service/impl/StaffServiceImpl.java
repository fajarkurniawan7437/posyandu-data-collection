package com.posyandu.data.service.impl;

import com.posyandu.data.entity.Staff;
import com.posyandu.data.model.request.StaffRequest;
import com.posyandu.data.model.response.StaffResponse;
import com.posyandu.data.repository.StaffRepository;
import com.posyandu.data.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Override
    public Staff create(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff getByAuth(Authentication authentication) {
        return staffRepository.findByUserCredential_Email(authentication.getName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @Override
    public Staff getById(String id) {
        return staffRepository.findStaffById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found"));
    }

    @Override
    public List<StaffResponse> getAll() {
        List<Staff> staffList = staffRepository.findAllStaff();

        return staffList.stream()
                .map(staff -> toResponse(staff)).collect(Collectors.toList());
    }

    @Override
    public StaffResponse update(StaffRequest request) {
        Staff currentStaff = getById(request.getId());

        if (currentStaff != null) {
            currentStaff.setName(request.getName());
            currentStaff.setPhone(request.getPhone());

            Staff updateStaff = staffRepository.save(currentStaff);
            return toResponse(currentStaff);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
//        Staff data = getById(id);
        staffRepository.deleteStaffById(id);
    }

    private StaffResponse toResponse(Staff staff){
        return StaffResponse.builder()
                .name(staff.getName())
                .phone(staff.getPhone())
                .build();
    }
}
