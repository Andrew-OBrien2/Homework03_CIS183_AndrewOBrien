package com.example.homework03_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity
{
    //Static variable to indicate a new student was added
    public static boolean studentAdded = false;

    EditText et_j_addStudent_firstName;
    EditText et_j_addStudent_lastName;
    EditText et_j_addStudent_username;
    EditText et_j_addStudent_gpa;
    EditText et_j_addStudent_email;
    EditText et_j_addStudent_age;
    Spinner sp_j_addStudent_major;
    Button btn_j_addStudent_back;
    Button btn_j_addStudent_addStudent;
    TextView tv_j_addStudent_addNewMajor;
    TextView tv_j_addStudent_error;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        et_j_addStudent_firstName   = findViewById(R.id.et_v_addStudent_firstName);
        et_j_addStudent_lastName    = findViewById(R.id.et_v_addStudent_lastName);
        et_j_addStudent_username    = findViewById(R.id.et_v_addStudent_username);
        et_j_addStudent_gpa         = findViewById(R.id.et_v_addStudent_gpa);
        et_j_addStudent_email       = findViewById(R.id.et_v_addStudent_email);
        et_j_addStudent_age         = findViewById(R.id.et_v_addStudent_age);
        sp_j_addStudent_major       = findViewById(R.id.sp_v_addStudent_major);
        btn_j_addStudent_back       = findViewById(R.id.btn_v_addStudent_back);
        btn_j_addStudent_addStudent = findViewById(R.id.btn_v_addStudent_addStudent);
        tv_j_addStudent_addNewMajor = findViewById(R.id.tv_v_addStudent_addNewMajor);
        tv_j_addStudent_error       = findViewById(R.id.tv_v_addStudent_error);

        //Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        addStudentButtonClickListener();
        backButtonClickListener();
        addNewMajorClickListener();

        ArrayList<String> majors = dbHelper.getAllMajors();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, majors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the adapter to the spinner
        sp_j_addStudent_major.setAdapter(adapter);

    }

    private void addStudentButtonClickListener()
    {
        btn_j_addStudent_addStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!dbHelper.doesUsernameExists(et_j_addStudent_username.getText().toString()))
                {
                    studentAdded = true;
                    addStudent();
                    tv_j_addStudent_error.setVisibility(View.INVISIBLE);
                }
                else
                {
                    tv_j_addStudent_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void backButtonClickListener()
    {
        btn_j_addStudent_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AddStudent.this, MainActivity.class));
            }
        });
    }

    private void addNewMajorClickListener()
    {
        tv_j_addStudent_addNewMajor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AddStudent.this, AddNewMajor.class));
            }
        });
    }

    private void addStudent()
    {
        String username = et_j_addStudent_username.getText().toString();
        String firstName = et_j_addStudent_firstName.getText().toString();
        String lastName = et_j_addStudent_lastName.getText().toString();
        String email = et_j_addStudent_email.getText().toString();
        int age = Integer.parseInt(et_j_addStudent_age.getText().toString());
        float gpa = Float.parseFloat(et_j_addStudent_gpa.getText().toString());
        String major = sp_j_addStudent_major.getSelectedItem().toString();


        Student newStudent = new Student(username, firstName, lastName, email, age, gpa, major);

        dbHelper.addStudentToDB(newStudent);

        clearFields();
    }

    private void clearFields()
    {
        et_j_addStudent_firstName.setText("");
        et_j_addStudent_lastName.setText("");
        et_j_addStudent_username.setText("");
        et_j_addStudent_gpa.setText("");
        et_j_addStudent_email.setText("");
        et_j_addStudent_age.setText("");
        sp_j_addStudent_major.setSelection(0);
    }
}