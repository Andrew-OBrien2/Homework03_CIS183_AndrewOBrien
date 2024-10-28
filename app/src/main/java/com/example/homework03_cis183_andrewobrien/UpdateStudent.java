package com.example.homework03_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    }

    private void updateStudentButtonClickListener()
    {
        btn_j_updateStudent_updateStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

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
                startActivity(new Intent(UpdateStudent.this, StudentDetails.class));
            }
        });
    }
}