package com.salary.salary.salary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.salary.salary.salary.contextconfig.App;
import com.salary.salary.salary.domain.Employee;
import com.salary.salary.salary.repository.impl.EmployeeRepo;

import java.util.ArrayList;
import java.util.Set;

public class DisplayRecords extends Activity {
    private EmployeeRepo repo;

    private ArrayList<String> employees;
    private ArrayAdapter arrayAdapter;
    GridView employeeGridView;
       EditText  search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);
         search=(EditText) findViewById(R.id.txtSearch);


    repo= new EmployeeRepo(this.getApplicationContext());
   loadGrid();

    }

public void loadGrid(){
    employees = new ArrayList<>();

    Cursor cursor =    repo.getAll();

    if (cursor.moveToNext()) {
        do {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String lastName = cursor.getString(2);
            String job = cursor.getString(3);
            String rate = cursor.getString(4);
            String hours = cursor.getString(5);
            String salary = cursor.getString(6);

            employees.add(id);
            employees.add(name);
           // employees.add(lastName);
            employees.add(job);
            //  employees.add(rate);
            // employees.add(hours);
            employees.add(salary);
        } while (cursor.moveToNext());
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employees);

        employeeGridView = (GridView) findViewById(R.id.gridView);
        employeeGridView.setAdapter(arrayAdapter);


    }

}
    public void delete(View view) {


    }

    public void update(View view) {


        Intent intent = new Intent(getApplicationContext(), AddRecord.class);

try {
    String id = search.getText().toString();
    Long idLong = Long.parseLong(id);
    Employee employee = repo.findById(idLong);
    if(employee==null){
        Toast.makeText(getBaseContext(), "not found", Toast.LENGTH_LONG).show();

    }
    else {
        intent.putExtra("emp", employee);
        intent.putExtra("key", "UPDATE");
        startActivity(intent);
    }
}
catch (Exception e){
    Toast.makeText(getBaseContext(), "not found"+ e.getMessage(), Toast.LENGTH_LONG).show();

}

    }

    public void add(View view) {
        Intent intent = new Intent(getApplicationContext(),AddRecord.class);
        intent.putExtra("key","ADD");
        startActivity(intent);
    }

    public void search(View view) {
        String id=search.getText().toString();
        try {
            Long idLong = Long.parseLong(id);
            Employee employee = repo.findById(idLong);
            if (employee == null) {
                Toast.makeText(getBaseContext(), "not found", Toast.LENGTH_LONG).show();
            } else {


            }
        }
            catch(Exception e){
                Toast.makeText(getBaseContext(), "make sure you entered right format", Toast.LENGTH_LONG).show();
            }

    }

    public void deleteRecord(View view) {
        try {
            String id = search.getText().toString();
            Long idLong = Long.parseLong(id);
            Employee employee = repo.findById(idLong);
            repo.remove(employee);
            loadGrid();
        }
        catch(Exception e)
        {
            Toast.makeText(getBaseContext(), "not found" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}


