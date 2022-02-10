package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/reports/{id}")
    public ReportingStructure read(@PathVariable String employeeIdentification) {
        LOG.debug("Received reporting structure read request for id [{}]", employeeIdentification);

        return reportingService.read(employeeIdentification);
    }

}