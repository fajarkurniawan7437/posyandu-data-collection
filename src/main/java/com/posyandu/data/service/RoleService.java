package com.posyandu.data.service;

import com.posyandu.data.entity.Role;
import com.posyandu.data.entity.constant.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}
