package com.salary.salary.salary;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.salary.salary.salary.domain.Employee;
import com.salary.salary.salary.repository.impl.EmployeeRepo;

import junit.framework.Assert;

import java.util.Set;


public class EmployeeTest extends AndroidTestCase {
    private static final String TAG="Employee Test";
    private Long id =1L;
    private EmployeeRepo employeeRepository;

    public void testAddFindREmoveEdit(){
       employeeRepository = new EmployeeRepo(this.getContext());
        //CREATING EMPLOYEE

        Employee makeEmployee=new Employee.EmployeeBuilder()

                .name("salary")
                .surname("salary")
                .jobTitle("salary")
                .rate(2.0)
                .hoursWorked(6)
                .salary()
                .build();
        Employee madeEmployee =employeeRepository.add(makeEmployee);
        id=madeEmployee.getId();
        Assert.assertNotNull(TAG+" CREATING",madeEmployee);

       //READ ALL
        Cursor employees=employeeRepository.getAll();
        Assert.assertTrue(TAG+" READ ALL",employees.getCount()>0);

        /*//FIND EMPLOYEE BY ID
        Employee EmployeeFound=employeeRepository.findById(id);

        Assert.assertNotNull(TAG+" FIND BY ID",EmployeeFound);

        //UPDATE

        Employee updatedEmployee=new Employee.EmployeeBuilder()
                .copy(EmployeeFound)
                .name("admn")
                .build();
        employeeRepository.update(updatedEmployee);
        Employee newEmployee=employeeRepository.findById(id);
        Assert.assertEquals(TAG+" UPDATE","admn",newEmployee.getName());

        //DELETE
        employeeRepository.remove(updatedEmployee);
        Employee employeeDelete =employeeRepository.findById(id);
        Assert.assertNull(TAG+"  DELETE", employeeDelete);

        //DELETE ALL
        employeeRepository.removeAll();
        Set<Employee> allEmployees=employeeRepository.findAll();
        Assert.assertEquals(TAG+" DELETE ALL",0,allEmployees.size());
*/

    }
}
