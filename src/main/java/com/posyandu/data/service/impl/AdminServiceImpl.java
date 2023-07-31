package com.posyandu.data.service.impl;

import com.posyandu.data.entity.Admin;
import com.posyandu.data.repository.AdminRepository;
import com.posyandu.data.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }
}
