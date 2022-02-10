package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepositoryObject;

    @Autowired
    private EmployeeRepository employeeRepositoryObject;

    @Override
    public Compensation create(Compensation compensationObject) {
        LOG.debug("Creating compensation [{}]", compensationObject);

        String employeeID = compensationObject.getEmployeeIdentification();

        Employee employeeObject = employeeRepositoryObject.findByEmployeeId(employeeID);

        // Check to see if employee object is null
        if(employeeObject == null) {
            LOG.debug("Compensation invalid employeeId: [{}]", employeeID);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId: " + employeeID);
        }

        if (compensationRepositoryObject.findByEmployeeId(employeeID) != null) {
            // a compensation for this employee already exists
            LOG.debug("Compensation for employeeId: [{}] already exists!", employeeID);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Compensation for employeeId: " + employeeID + " already exists");
        }

        compensationRepositoryObject.insert(compensationObject);

        return compensationObject;
    }

    @Override
    public Compensation read(String employeeIdentification) {
        LOG.debug("Reading compensation by employeeId [{}]", employeeIdentification);

        Compensation compensationVariable = compensationRepositoryObject.findByEmployeeId(employeeIdentification);

        if (compensationVariable == null) {
            LOG.debug("Compensation invalid employeeId: [{}]", employeeIdentification);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId: " + employeeIdentification);
        }

        return compensationVariable;
    }
}
