package com.mindex.challenge.data;

/** Java File: ReportingStructure.java
 * @Author: Faraz Mamaghani
 */

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Compensation {

    /** Data Members*/
    private String employeeId;
    private float employeeSalary;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date employeeDate;

    /** Constructors */
    // Blank Constructor
    public Compensation() {

    }

    /** Getters and setters */

    /** Getters */
    public String getEmployeeIdentification() {
        return employeeId;
    }

    public float getEmployeeSalary() {
        return employeeSalary;
    }

    public Date getEmployeeDate() {
        return employeeDate;
    }

    /** Setters */
    public void setEmployeeIdentification(String employeeIdentificationObject) {
        this.employeeId = employeeIdentificationObject;
    }

    public void setEmployeeSalary(float employeeSalaryObject) {
        this.employeeSalary = employeeSalaryObject;
    }

    public void setEmployeeDate(Date employeeDateObject) {
        this.employeeDate = employeeDateObject;
    }

}
