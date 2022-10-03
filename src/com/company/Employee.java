package com.company;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class Employee implements Comparable<Employee>{
    private String empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Employee(String empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.empId = empId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        Employee other = (Employee) obj;
        return Objects.equals(empId, other.empId) && Objects.equals(projectId, other.projectId)
                && Objects.equals(dateFrom, other.dateFrom)
                && Objects.equals(dateTo, other.dateTo);
    }

    @Override
    public int compareTo(Employee o) {
        return empId.compareTo(o.empId);
    }
    public static Comparator<Employee> COMPARE_BY_DATE_FROM = new Comparator<Employee>() {
        public int compare(Employee one, Employee other) {
            return one.dateFrom.compareTo(other.dateFrom);
        }
    };
    public static Comparator<Employee> COMPARE_BY_DATE_TO = new Comparator<Employee>() {
        public int compare(Employee one, Employee other) {
            return one.dateTo.compareTo(other.dateTo);
        }
    };
}
