package com.posyandu.data.repository;

import com.posyandu.data.entity.Role;
import com.posyandu.data.entity.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {

    Optional<Role> findByRole(ERole category);

}