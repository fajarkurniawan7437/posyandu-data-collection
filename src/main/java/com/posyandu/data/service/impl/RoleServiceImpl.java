package com.posyandu.data.service.impl;

import com.posyandu.data.entity.Role;
import com.posyandu.data.entity.constant.ERole;
import com.posyandu.data.repository.RoleRepository;
import com.posyandu.data.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role).orElseGet(() ->
                roleRepository.save(Role.builder().role(role).build()));
    }
}
