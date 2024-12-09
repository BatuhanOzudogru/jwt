package com.batuhanozudogru.jwt.controller;

import com.batuhanozudogru.jwt.dto.DtoEmployee;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface RestEmployeeController {

    DtoEmployee findEmployeeById(@Valid @NotNull Long id);
}
