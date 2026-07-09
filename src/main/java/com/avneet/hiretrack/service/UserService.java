package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.RegisterRequest;

public interface UserService {

    String register(RegisterRequest request);

}