package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation create(Compensation compensationObject);
    Compensation read(String employeeIdentification);
}
