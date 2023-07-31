package com.posyandu.data.service;

import com.posyandu.data.model.request.AuthRequest;
import com.posyandu.data.model.response.LoginResponse;
import com.posyandu.data.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest request);
    RegisterResponse registerStaff(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
