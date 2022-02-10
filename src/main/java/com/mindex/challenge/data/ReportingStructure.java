package com.mindex.challenge.data;

/** Java File: ReportingStructure.java
 * @Author: Faraz Mamaghani
 */

public class ReportingStructure {

    /** Data Members*/
    private Employee employee;
    private int numberOfReports;

    /** Constructors */
    // Blank Constructor
    public ReportingStructure() {

    }

    // Constructor with parameter
    public ReportingStructure(Employee employeeObject){
        this.employee = employeeObject;
    }

    /** Getters and setters */

    /** Getters */
    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    /** Setters */
    public void setEmployee(Employee employeeObject) {
        this.employee = employeeObject;
    }

    public void setNumberOfReports(int totalNumberOfReports) {
        this.numberOfReports = totalNumberOfReports;
    }

}
