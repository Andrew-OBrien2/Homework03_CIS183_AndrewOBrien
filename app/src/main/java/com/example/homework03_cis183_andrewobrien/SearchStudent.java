package com.example.homework03_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchStudent extends AppCompatActivity
{
    EditText et_j_search_username;
    EditText et_j_search_firstName;
    EditText et_j_search_lastName;
    EditText et_j_search_gpa;
    Spinner sp_j_search_gpaCategory;
    Spinner sp_j_search_major;
    ListView lv_j_search_searchResults;
    Button btn_j_search_back;
    Button btn_j_search_search;
    DatabaseHelper dbHelper;

    SearchListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_student);

        et_j_search_username      = findViewById(R.id.et_v_search_username);
        et_j_search_firstName     = findViewById(R.id.et_v_search_firstName);
        et_j_search_lastName      = findViewById(R.id.et_v_search_lastName);
        et_j_search_gpa           = findViewById(R.id.et_v_search_gpa);
        sp_j_search_major         = findViewById(R.id.sp_v_search_major);
        lv_j_search_searchResults = findViewById(R.id.lv_v_search_searchResults);
        btn_j_search_back         = findViewById(R.id.btn_v_search_back);
        btn_j_search_search       = findViewById(R.id.btn_v_search_search);

        dbHelper = new DatabaseHelper(this);

        searchButtonClickListener();
        backStudentButtonClickListener();

        ArrayList<String> majors = dbHelper.getAllMajors();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, majors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the adapter to the spinner
        sp_j_search_major.setAdapter(adapter);

    }

    private void searchButtonClickListener()
    {
        btn_j_search_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                fillListView();
            }
        });
    }

    private void backStudentButtonClickListener()
    {
        btn_j_search_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SearchStudent.this, MainActivity.class));
            }
        });
    }

    private void fillListView()
    {
        String username = et_j_search_username.getText().toString();
        String firstName = et_j_search_firstName.getText().toString();
        String lastName = et_j_search_lastName.getText().toString();
        String gpaString = et_j_search_gpa.getText().toString();
        String major = sp_j_search_major.getSelectedItem().toString();


        //call the database method to find students based on criteria
        ArrayList<Student> students = dbHelper.findStudentsGivenCriteria(username, firstName, lastName, gpaString, major);

        //initialize the adapter with the retrieved student list and set it to the ListView
        adapter = new SearchListAdapter(this, students, dbHelper);
        lv_j_search_searchResults.setAdapter(adapter);
    }
}