package com.posyandu.data.service.impl;

import com.posyandu.data.entity.*;
import com.posyandu.data.entity.constant.ERole;
import com.posyandu.data.model.request.AuthRequest;
import com.posyandu.data.model.response.LoginResponse;
import com.posyandu.data.model.response.RegisterResponse;
import com.posyandu.data.repository.UserCredentialRepository;
import com.posyandu.data.security.BCryptUtils;
import com.posyandu.data.security.JwtUtils;
import com.posyandu.data.service.AdminService;
import com.posyandu.data.service.AuthService;
import com.posyandu.data.service.RoleService;
import com.posyandu.data.service.StaffService;
import com.posyandu.data.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptUtils bCryptUtils;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;
    private final ValidationUtil validationUtil;
    private final AdminService adminService;
    private final StaffService staffService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(credential);

            Admin admin = Admin.builder()
                    .email(request.getEmail())
                    .userCredential(credential)
                    .build();
            adminService.create(admin);
            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerStaff(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_STAFF);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(credential);
            Staff staff = Staff.builder()
                    .status(true)
                    .userCredential(credential)
                    .build();
            staffService.create(staff);
            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String token = jwtUtils.generateToken(userDetails.getEmail());

        return LoginResponse.builder()
                .email(userDetails.getEmail())
                .roles(roles)
                .token(token)
                .build();
    }
}
