package com.salary.salary.salary;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.salary.salary.salary.domain.Employee;
import com.salary.salary.salary.factory.EmployeeFactory;
import com.salary.salary.salary.repository.impl.EmployeeRepo;

public class AddRecord extends Activity {

    private TextView idNumber;
    private EditText name;
    private EditText lastName;
    private EditText job;
    private EditText rate;
    private EditText hours;
    private TextView salary;
    private Button button;
    private EmployeeRepo repo;
    Bundle bundle;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        repo = new EmployeeRepo(this.getApplicationContext());
        //
        idNumber = (TextView) findViewById(R.id.txtID);

        name = (EditText) findViewById(R.id.txtName);
        lastName = (EditText) findViewById(R.id.txtSurname);
        job = (EditText) findViewById(R.id.txtJob);
        rate = (EditText) findViewById(R.id.txtrate);
        hours = (EditText) findViewById(R.id.txtHours);
        salary = (TextView) findViewById(R.id.txtSalary);
        Intent getIntent= getIntent();
        bundle= getIntent.getExtras();
         key=(String)bundle.get("key");
        if (key.equals("UPDATE")) {
            if (bundle != null){
            Employee employee = (Employee) bundle.get("emp");
                String strRate= Double.toString(employee.getRate());
            idNumber.setText(Long.toString( employee.getId()));
            name.setText(employee.getName().toString());
            lastName.setText(employee.getSurname().toString());
            job.setText(employee.getJobTitle().toString());
            rate.setText(strRate);
            hours.setText(Integer.toString( employee.getHours()));
            salary.setText(Double.toString( employee.getSalary()));
            repo.update(employee);
        }
        }


    }

    public void saveRecord(View view) {


        try {
            if(bundle==null) {
            }
            else {
                if (key.equals("UPDATE")) {
                    Employee employee =(Employee) bundle.get("emp");
                     Employee update= new Employee.EmployeeBuilder()
                            .copy(employee)
                             .id(Long.parseLong(idNumber.getText().toString()))
                            .name(name.getText().toString())
                            .surname(lastName.getText().toString())
                            .jobTitle(job.getText().toString())
                            .hoursWorked(Integer.parseInt(hours.getText().toString()))
                            .rate(Double.parseDouble(rate.getText().toString()))
                            .salary()
                            .build();

                    repo.update(update);
                    Intent intent = new Intent(getApplicationContext(),DisplayRecords.class);
                    startActivity(intent);


                } else {
                    String hRate = rate.getText().toString();
                    if (hRate.contains(".")) {

                    } else {
                        hRate = hRate.concat(".0");
                    }
                    Employee employee = new EmployeeFactory().createEmployee(
                            name.getText().toString(), lastName.getText().toString(),
                            job.getText().toString(), Double.parseDouble(hRate),
                            Integer.parseInt(hours.getText().toString()));
                    repo.add(employee);
                    clear();
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getBaseContext(), e.getMessage()+"make sure you entered valid input", Toast.LENGTH_LONG).show();

        } catch (Exception e) {

            
            Toast.makeText(getBaseContext(), e.getMessage()+"something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void calculateSal(View view) {
        {
            try {
                Double sal = Integer.parseInt(hours.getText().toString()) * Double.parseDouble(rate.getText().toString());
                int b;
                double n;

                salary.setText(Double.toString( sal));

            } catch (NumberFormatException e) {
                Toast.makeText(getBaseContext(), e.getMessage()+"make sure you entered valid input", Toast.LENGTH_LONG).show();

            } catch (Exception e) {

            }
        }

    }
public void clear(){
    idNumber.setText("");
    name.setText("");
    lastName.setText("");
    job.setText("");
    rate.setText("");
    hours.setText("");
    salary.setText("");
}
    public void home(View view) {
        Intent intent = new Intent(getApplicationContext(),DisplayRecords.class);
        startActivity(intent);

    }
}



