package com.example.homework03_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDetails extends AppCompatActivity
{
    TextView tv_j_studentDetails_firstName;
    TextView tv_j_studentDetails_lastName;
    TextView tv_j_studentDetails_username;
    TextView tv_j_studentDetails_gpa;
    TextView tv_j_studentDetails_email;
    TextView tv_j_studentDetails_age;
    TextView tv_j_studentDetails_major;
    Button btn_j_studentDetails_updateInfo;
    Button btn_j_studentDetails_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_details);

        tv_j_studentDetails_firstName = findViewById(R.id.tv_v_studentDetails_firstName);
        tv_j_studentDetails_lastName = findViewById(R.id.tv_v_studentDetails_lastName);
        tv_j_studentDetails_username = findViewById(R.id.tv_v_studentDetails_username);
        tv_j_studentDetails_gpa = findViewById(R.id.tv_v_studentDetails_gpa);
        tv_j_studentDetails_email = findViewById(R.id.tv_v_studentDetails_email);
        tv_j_studentDetails_age = findViewById(R.id.tv_v_studentDetails_age);
        tv_j_studentDetails_major = findViewById(R.id.tv_v_studentDetails_major);
        btn_j_studentDetails_updateInfo = findViewById(R.id.btn_v_studentDetails_updateInfo);
        btn_j_studentDetails_back = findViewById(R.id.btn_v_studentDetails_back);

        updateInfoButtonClickListener();
        backButtonClickListener();

    }

    private void updateInfoButtonClickListener()
    {
        btn_j_studentDetails_updateInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    private void backButtonClickListener()
    {
        btn_j_studentDetails_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(StudentDetails.this, MainActivity.class));
            }
        });
    }
}