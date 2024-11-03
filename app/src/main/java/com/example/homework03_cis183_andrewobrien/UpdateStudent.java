package com.example.homework03_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class UpdateStudent extends AppCompatActivity
{
    EditText et_j_updateStudent_firstName;
    EditText et_j_updateStudent_lastName;
    EditText et_j_updateStudent_username;
    EditText et_j_updateStudent_gpa;
    EditText et_j_updateStudent_email;
    EditText et_j_updateStudent_age;
    Spinner sp_j_updateStudent_major;
    Button btn_j_updateStudent_back;
    Button btn_j_updateStudent_updateStudent;

    DatabaseHelper dbHelper;
    public static boolean studentUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_student);

        //connecting GUI to java
        et_j_updateStudent_firstName       = findViewById(R.id.et_v_updateStudent_firstName);
        et_j_updateStudent_lastName        = findViewById(R.id.et_v_updateStudent_lastName);
        et_j_updateStudent_username        = findViewById(R.id.et_v_updateStudent_username);
        et_j_updateStudent_gpa             = findViewById(R.id.et_v_updateStudent_gpa);
        et_j_updateStudent_email           = findViewById(R.id.et_v_updateStudent_email);
        et_j_updateStudent_age             = findViewById(R.id.et_v_updateStudent_age);
        sp_j_updateStudent_major           = findViewById(R.id.sp_v_updateStudent_major);
        btn_j_updateStudent_back           = findViewById(R.id.btn_v_updateStudent_back);
        btn_j_updateStudent_updateStudent  = findViewById(R.id.btn_v_updateStudent_updateStudent);

        updateStudentButtonClickListener();
        backButtonClickListener();

        dbHelper = new DatabaseHelper(this);

        ArrayList<String> majors = dbHelper.getAllMajors();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, majors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the adapter to the spinner
        sp_j_updateStudent_major.setAdapter(adapter);


        if (getIntent() != null)
        {
            String username = getIntent().getStringExtra("username");
            String firstName = getIntent().getStringExtra("firstName");
            String lastName = getIntent().getStringExtra("lastName");
            String email = getIntent().getStringExtra("email");
            int age = getIntent().getIntExtra("age", 0);
            float gpa = getIntent().getFloatExtra("gpa", 0f);
            String major = getIntent().getStringExtra("major");

            // Set the data to TextViews
            et_j_updateStudent_username.setText(username);
            et_j_updateStudent_firstName.setText(firstName);
            et_j_updateStudent_lastName.setText(lastName);
            et_j_updateStudent_email.setText(email);
            et_j_updateStudent_age.setText(String.valueOf(age));
            et_j_updateStudent_gpa.setText(String.valueOf(gpa));

            //setting the spinners position
            int spinnerPosition = adapter.getPosition(major);
            sp_j_updateStudent_major.setSelection(spinnerPosition);

        }
    }

    private void updateStudentButtonClickListener()
    {
        btn_j_updateStudent_updateStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Get the new values from the EditText fields and Spinner
                String oldUsername = getIntent().getStringExtra("username");
                String username = et_j_updateStudent_username.getText().toString();
                String firstName = et_j_updateStudent_firstName.getText().toString();
                String lastName = et_j_updateStudent_lastName.getText().toString();
                String email = et_j_updateStudent_email.getText().toString();
                String age = et_j_updateStudent_age.getText().toString();
                String gpa = et_j_updateStudent_gpa.getText().toString();
                String major = sp_j_updateStudent_major.getSelectedItem().toString();

                // Execute the SQL statement
                dbHelper.updateStudent(oldUsername, username,firstName,lastName,email,age,gpa,major);

                // Go back to StudentDetails with the updated info
                Intent intent = new Intent(UpdateStudent.this, StudentDetails.class);
                intent.putExtra("username", username);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("email", email);
                intent.putExtra("age", Integer.parseInt(age));
                intent.putExtra("gpa", Float.parseFloat(gpa));
                intent.putExtra("major", dbHelper.getMajorPrefix(major));

                studentUpdated = true;

                Log.d("DID DATABASE UPDATE USERNAME", username);
                startActivity(intent);
            }
        });
    }

    private void backButtonClickListener()
    {
        btn_j_updateStudent_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //this is easier than going back and getting the information of the student again
                //note to self - finish() can be used in the final project for having a side menu display all the pages
                finish();
            }
        });
    }
}