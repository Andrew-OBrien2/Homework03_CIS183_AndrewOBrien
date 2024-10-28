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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button btn_j_main_searchStudent;
    Button btn_j_main_addStudent;
    ListView lv_j_main_studentList;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //connecting GUI to java
        btn_j_main_searchStudent = findViewById(R.id.btn_v_main_searchStudent);
        btn_j_main_addStudent    = findViewById(R.id.btn_v_main_addStudent);
        lv_j_main_studentList    = findViewById(R.id.lv_v_main_studentList);

        //make a new instance of the dbHelper.
        dbHelper = new DatabaseHelper(this);

        //initialize all of the tables with dummy data
        dbHelper.initAllTables();

        searchStudentButtonClickListener();
        addStudentButtonClickListener();

        // Get all students from the database
        ArrayList<Student> students = dbHelper.getAllStudents();

        // Now, you can use an adapter to display the data in the ListView
        StudentListAdapter adapter = new StudentListAdapter(this, students); // Assuming you have a StudentAdapter class
        lv_j_main_studentList.setAdapter(adapter);

        if (AddStudent.studentAdded)
        {
            updateStudentList(adapter); //update the ListView
            AddStudent.studentAdded = false; //reset the bool
        }
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

    private void updateStudentList(StudentListAdapter adapter)
    {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<Student> updatedStudents = dbHelper.getAllStudents(); // Get the updated list
        adapter.clear(); // Clear existing items
        adapter.addAll(updatedStudents); // Add the updated items
        adapter.notifyDataSetChanged(); // Notify the adapter to refresh the ListView
    }
}