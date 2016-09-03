package com.salary.salary.salary.factory;


import com.salary.salary.salary.domain.Employee;

public class EmployeeFactory {


    private static EmployeeFactory employeeFactory =null;

    public EmployeeFactory getInstance()
    {
        if (employeeFactory ==null)
            employeeFactory =new EmployeeFactory();
        return employeeFactory;
    }


    public Employee createEmployee(String name, String surname,String job,double rate,int hours) {
        Employee objEmployee = new Employee.EmployeeBuilder()

                .name(name)
                .surname(surname)
                .jobTitle(job)
                .rate(rate)
                .hoursWorked(hours)
                .salary()
                .build();
        return objEmployee;
    }


}
