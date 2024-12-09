package com.batuhanozudogru.jwt.service;

import com.batuhanozudogru.jwt.dto.DtoEmployee;

import java.util.Optional;

public interface EmployeeService {

    DtoEmployee findEmployeeById(Long id);
}
