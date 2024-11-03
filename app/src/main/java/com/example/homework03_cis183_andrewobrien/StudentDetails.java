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

    private Student student;

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

        if (getIntent() != null)
        {
            String username = getIntent().getStringExtra("username");
            String firstName = getIntent().getStringExtra("firstName");
            String lastName = getIntent().getStringExtra("lastName");
            String email = getIntent().getStringExtra("email");
            int age = getIntent().getIntExtra("age", 0);
            float gpa = getIntent().getFloatExtra("gpa", 0f);
            String major = getIntent().getStringExtra("major");

            student = new Student(username, firstName, lastName, email, age, gpa, major);

            // Set the data to TextViews
            tv_j_studentDetails_username.setText(username);
            tv_j_studentDetails_firstName.setText(firstName);
            tv_j_studentDetails_lastName.setText(lastName);
            tv_j_studentDetails_email.setText(email);
            tv_j_studentDetails_age.setText(String.valueOf(age));
            tv_j_studentDetails_gpa.setText(String.valueOf(gpa));
            tv_j_studentDetails_major.setText(major);
        }

    }

    private void updateInfoButtonClickListener()
    {
        btn_j_studentDetails_updateInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DatabaseHelper dbHelper = new DatabaseHelper(StudentDetails.this);

                Intent intent = new Intent(StudentDetails.this, UpdateStudent.class);
                intent.putExtra("username", student.getUsername());
                intent.putExtra("firstName", student.getFirstName());
                intent.putExtra("lastName", student.getLastName());
                intent.putExtra("email", student.getEmail());
                intent.putExtra("age", student.getAge());
                intent.putExtra("gpa", student.getGpa());
                intent.putExtra("major", dbHelper.getMajorName(student.getMajor()));
                startActivity(intent);
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