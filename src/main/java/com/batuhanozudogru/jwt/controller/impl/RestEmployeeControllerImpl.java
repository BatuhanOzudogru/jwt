package com.batuhanozudogru.jwt.controller.impl;

import com.batuhanozudogru.jwt.controller.RestEmployeeController;
import com.batuhanozudogru.jwt.dto.DtoEmployee;
import com.batuhanozudogru.jwt.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class RestEmployeeControllerImpl implements RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@Valid @NotNull @PathVariable(value = "id") Long id) {
        return employeeService.findEmployeeById(id);
    }
}
