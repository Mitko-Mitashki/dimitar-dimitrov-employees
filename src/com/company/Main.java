package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("Please enter file path and name in field path: \n");
        String path = "<---Enter the path and file name here--->";
        String line = "";
        List<Employee> employees = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                Employee employee = createEmployee(attributes);
                employees.add(employee);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data from file: \n");
        employees.forEach(employee -> {

            System.out.println(employee.getEmpId() + " " + employee.getProjectId() +
                    " " + employee.getDateFrom().toString() + " " + employee.getDateTo().toString());
        });


        List<Employee> list = new ArrayList<>();
        long daysWorkedTogether = 0;
        List<Result> finalResult = new ArrayList<>();

        LocalDate dateFromFirst;
        LocalDate dateToFirst;
        LocalDate dateFromSecond;
        LocalDate dateToSecond;
        LocalDate startDate;
        LocalDate endDate;
        long allDays = 0L;
        long initPeriod= 0L ;
        long endPeriod= 0L;
        Result finalResultPrint = new Result();

        for (int i = 0; i< employees.size(); i++) {


            int first = employees.get(i).getProjectId();

            list = employees.stream()
                    .filter(p->p.getProjectId()==first)
                    .collect(Collectors.toList());
            Collections.sort(list, Employee.COMPARE_BY_DATE_FROM);
            if(list.size()>1) {
                for (int j = 0; j <list.size(); j++) {
                    for(int k = j+1; k< list.size(); k++) {

                        dateFromFirst = list.get(j).getDateFrom();
                        dateToFirst = list.get(j).getDateTo();
                        dateFromSecond = list.get(k).getDateFrom();
                        dateToSecond = list.get(k).getDateTo();
                        startDate = compareDate(dateFromFirst, dateFromSecond);
                        endDate = compareDateEnd(dateToFirst, dateToSecond);
                        allDays = ChronoUnit.DAYS.between(startDate, endDate);
                        initPeriod = Math.abs(ChronoUnit.DAYS.between(dateFromFirst, dateFromSecond));
                        endPeriod = Math.abs(ChronoUnit.DAYS.between(dateToFirst, dateToSecond));


                        daysWorkedTogether = allDays-initPeriod-endPeriod;
                        Result result = new Result(daysWorkedTogether, list.get(j).getEmpId(),
                                list.get(k).getEmpId(), first);
                        finalResult.add(result);
                        Collections.sort(finalResult, Result.COMPARE_BY_WORKED_DAYS);
                        finalResultPrint = finalResult.get(finalResult.size()-1);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("The result is: \n");
        System.out.println("Employee Id#1: " + finalResultPrint.getEmpId_1()
                + ", Employee Id#2: " + finalResultPrint.getEmpId_2()
                + ", Project Id: " + finalResultPrint.getProjectId()
                + ", Days worked: " + finalResultPrint.getDaysWorked());
    }

    private static Employee createEmployee(String[] data)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String name = data[0];
        int projectId = Integer.parseInt(data[1].trim());
        LocalDate dateFrom =  LocalDate.parse(data[2].trim(), formatter);
        LocalDate dateTo =  LocalDate.parse(data[3].trim(), formatter);

        Employee employee = new Employee(name, projectId, dateFrom, dateTo);

        return employee;
    }

    private static LocalDate compareDate(LocalDate first, LocalDate second) {
        return first.compareTo(second)<=0 ? first : second;
    }

    private static LocalDate compareDateEnd(LocalDate first, LocalDate second) {
        return first.compareTo(second)>=0 ? first : second;
    }
}
