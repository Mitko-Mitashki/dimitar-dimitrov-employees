package com.company;

import java.util.Comparator;
import java.util.Objects;

public class Result implements Comparable<Result> {
    private long daysWorked;
    private String empId_1;
    private String empId_2;
    private int projectId;

    public Result(long daysWorked, String empId_1, String empId_2, int projectId) {
        this.daysWorked = daysWorked;
        this.empId_1 = empId_1;
        this.empId_2 = empId_2;
        this.projectId = projectId;
    }

    public Result() {

    }

    public long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(long daysWorked) {
        this.daysWorked = daysWorked;
    }

    public String getEmpId_1() {
        return empId_1;
    }

    public void setEmpId_1(String empId_1) {
        this.empId_1 = empId_1;
    }

    public String getEmpId_2() {
        return empId_2;
    }

    public void setEmpId_2(String empId_2) {
        this.empId_2 = empId_2;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
        Result other = (Result) obj;
        return Objects.equals(daysWorked, other.daysWorked) && Objects.equals(empId_1, other.empId_1)
                && Objects.equals(empId_2, other.empId_2)
                && Objects.equals(projectId, other.projectId);
    }

    @Override
    public int compareTo(Result o) {
        return 0;
    }

    public static Comparator<Result> COMPARE_BY_WORKED_DAYS = new Comparator<Result>() {
        public int compare(Result one, Result other) {
            return Long.compare(one.daysWorked, other.daysWorked);
        }
    };
}
