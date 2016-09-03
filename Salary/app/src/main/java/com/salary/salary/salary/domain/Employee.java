package com.salary.salary.salary.domain;

import java.io.Serializable;

public class Employee implements Serializable{
    private long id;
    private String name;
    private String jobTitle;
    private String surname;
    private double rate;
    private int hours;
    private double salary;



    private Employee (EmployeeBuilder employeeBuilder) {
        this.id = employeeBuilder.id;
        this.name = employeeBuilder.name;
        this.surname = employeeBuilder.surname;
        this.jobTitle = employeeBuilder.jobTitle;
        this.rate = employeeBuilder.rate;
        this.hours=employeeBuilder.hours;
        this.salary=employeeBuilder.salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getRate() {
        return rate;
    }

    public int getHours() {
        return hours;
    }

    public double getSalary() {
        return hours*rate;
    }

    public static class EmployeeBuilder
    {
        private long id;
        private String name;
        private String jobTitle;
        private String surname;
        private double rate;
        private int hours;
        private double salary;

        public EmployeeBuilder id(Long id)
        {
            this.id=id;
            return  this;
        }

        public EmployeeBuilder name(String name)
        {
            this.name= name;
            return this ;
        }

        public EmployeeBuilder surname(String surnam)
        {
            this.surname=surnam;
            return this;
        }
        public EmployeeBuilder jobTitle(String job)
        {
            this.jobTitle=job;
            return this;
        }
        public EmployeeBuilder hoursWorked(int hours){
            this.hours=hours;
            return  this;
        }
        public EmployeeBuilder rate(double rate){
            this.rate=rate;
            return this;
        }
        public EmployeeBuilder salary(){
            this.salary = rate*hours;
            return this;
        }

        public EmployeeBuilder copy(Employee employee)
        {
            this.id=employee.id;
            this.name=employee.name;
            this.surname=employee.surname;
            this.jobTitle=employee.jobTitle;
            this.rate=employee.rate;
            this.hours=employee.hours;
            this.salary=employee.salary;
            return this;
        }

        public Employee build()
        {
            return new Employee(this);
        }

    }

}
