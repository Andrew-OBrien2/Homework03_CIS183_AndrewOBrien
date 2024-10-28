package com.example.homework03_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    Button btn_j_main_searchStudent;
    Button btn_j_main_addStudent;
    ListView lv_j_main_studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //connecting GUI to java
        btn_j_main_searchStudent = findViewById(R.id.btn_v_main_searchStudent);
        btn_j_main_addStudent    = findViewById(R.id.btn_v_main_addStudent);
        lv_j_main_studentList    = findViewById(R.id.lv_v_main_studentList);

        searchStudentButtonClickListener();
        addStudentButtonClickListener();
    }

    private void searchStudentButtonClickListener()
    {
        btn_j_main_searchStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, SearchStudent.class));
            }
        });
    }

    private void addStudentButtonClickListener()
    {
        btn_j_main_addStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, AddStudent.class));
            }
        });
    }
}