package com.posyandu.data.security;

import com.posyandu.data.entity.Staff;
import com.posyandu.data.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurity {
    private final StaffService staffService;

    public boolean checkStaff(Authentication authentication, String staffId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Staff staff = staffService.getById(staffId);
        return staff.getUserCredential().getEmail().equals(userDetails.getUsername());
    }
}