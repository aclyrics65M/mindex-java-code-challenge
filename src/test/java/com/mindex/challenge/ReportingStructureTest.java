package com.mindex.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingService;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportingStructureTest {

    @Autowired
    private ReportingService reportStructureService;

    @SuppressWarnings("deprecation")
    @Test
    public void testTotalNumberOfReports() {

        // Checks numberOfReports for employee1
        ReportingStructure employee1 = reportStructureService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertEquals(java.util.Optional.of(new Integer(4)), employee1.getNumberOfReports());

        // Checks numberOfReports for employee2
        ReportingStructure employee2 = reportStructureService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        assertEquals(java.util.Optional.of(new Integer(0)), employee2.getNumberOfReports());

        // Checks numberOfReports for employee3
        ReportingStructure employee3 = reportStructureService.read("03aa1462-ffa9-4978-901b-7c001562cf6f");
        assertEquals(java.util.Optional.of(new Integer(2)), employee3.getNumberOfReports());
    }
}