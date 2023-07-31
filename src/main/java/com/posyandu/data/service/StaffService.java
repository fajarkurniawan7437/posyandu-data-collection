package com.posyandu.data.service;

import com.posyandu.data.entity.Staff;
import com.posyandu.data.model.request.StaffRequest;
import com.posyandu.data.model.response.StaffResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface StaffService {
    Staff create(Staff staff);
    Staff getByAuth(Authentication authentication);
    Staff getById(String id);
    List<StaffResponse> getAll();
    StaffResponse update(StaffRequest request);
    void deleteById(String id);
}
