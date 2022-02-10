package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.DirectReport;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

@Service
public class ReportingServiceImpl implements ReportingService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String employeeIdentification) {
        LOG.debug("Report for employee id [{}]", employeeIdentification);

        // Obtain the employee object through the employeeIdentification string parameter
        Employee employeeObject = employeeRepository.findByEmployeeId(employeeIdentification);

        // If the employee does not exist throw a ResponseStatusException
        if (employeeObject == null) {
            LOG.debug("Compensation invalid employeeId: [{}]", employeeIdentification);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId: " + employeeIdentification);
        }

        // Create ReportingStructure object creation and set number of reports
        ReportingStructure reportingStructureObject = new ReportingStructure(employeeObject);

        List<Employee> directReportsList = employeeObject.getDirectReports();
        int numberOfReports = directReportsList.size();

        for (Employee employeeVariable : directReportsList) {
            List<Employee> directProvide = employeeVariable.getDirectReports();
            if (directProvide != null) {
                numberOfReports = numberOfReports + employeeVariable.getDirectReports().size();
            }
        }

        reportingStructureObject.setNumberOfReports(numberOfReports);

        /* Return statement */
        return reportingStructureObject;
    }

    /* Method Name: calculateNumberOfReports
     *
     * @param: Employee employeeObject
     * @return: (int) totalNumberOfReports
     */
    public int calculateNumberOfReports(Employee employeeObject) {
        /* Initialize totalNumberOfReports to 0 */
        int totalNumberOfReports = 0;

        /* Create a Depth-First Search stack of employees */
        Stack<Employee> dfsEmployeeStack = new Stack<>();

        /* Initialize a Set object of already visited employeeIDs */
        HashSet<String> visitedEmployeeIdentifications = new HashSet<String>();

        /* Insert the first employee to the stack */
        dfsEmployeeStack.add(employeeObject);

        // Grand while-loop
        while(!dfsEmployeeStack.isEmpty()) {
            // Obtain the most recent employee and identification
            Employee recentEmployeeObject = dfsEmployeeStack.pop();
            String employeeIdentity = recentEmployeeObject.getEmployeeId();

            // Current employee visited checkpoint
            visitedEmployeeIdentifications.add(employeeIdentity);

            // Receive the current list of complete reports
            List<Employee> currentCompleteReportsList = recentEmployeeObject.getDirectReports();

            if(!currentCompleteReportsList.isEmpty()) {

                // for loop to iterate through each direct report
                for(Employee employeeVar: currentCompleteReportsList) {

                    // Obtain the identification string of the report
                    String reportIdentification = employeeVar.getEmployeeId();

                    // If statement to check if employee report identification has not been visited
                    if(!visitedEmployeeIdentifications.contains(reportIdentification)) {

                        totalNumberOfReports = totalNumberOfReports + 1;

                        // Find offspring employee
                        Employee offspringEmployee = employeeRepository.findByEmployeeId(reportIdentification);

                        if(employeeVar == null) {
                            throw new RuntimeException("Invalid employeeID: " + reportIdentification);
                        }

                        dfsEmployeeStack.push(offspringEmployee);
                    }

                }

            }

        }


        return totalNumberOfReports;
    }

}

