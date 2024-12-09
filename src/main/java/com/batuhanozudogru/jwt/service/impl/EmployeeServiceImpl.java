package com.batuhanozudogru.jwt.service.impl;

import com.batuhanozudogru.jwt.dto.DtoDepartment;
import com.batuhanozudogru.jwt.dto.DtoEmployee;
import com.batuhanozudogru.jwt.model.Department;
import com.batuhanozudogru.jwt.model.Employee;
import com.batuhanozudogru.jwt.repository.EmployeeRepository;
import com.batuhanozudogru.jwt.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DtoEmployee findEmployeeById(Long id) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoDepartment dtoDepartment = new DtoDepartment();

        Optional<Employee> byId = employeeRepository.findById(id);
        if(byId.isEmpty()){
            return null;
        }
        Employee employee = byId.get();
        Department department = employee.getDepartment();

        BeanUtils.copyProperties(employee, dtoEmployee);
        BeanUtils.copyProperties(department, dtoDepartment);

        dtoEmployee.setDepartment(dtoDepartment);
        return dtoEmployee;
    }
}
