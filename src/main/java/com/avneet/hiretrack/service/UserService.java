package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.LoginRequest;
import com.avneet.hiretrack.dto.RegisterRequest;

public interface UserService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}