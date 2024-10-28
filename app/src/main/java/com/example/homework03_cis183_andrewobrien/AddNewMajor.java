package com.example.homework03_cis183_andrewobrien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNewMajor extends AppCompatActivity
{
    EditText et_j_addMajor_majorName;
    EditText et_j_addMajor_majorPrefix;
    Button btn_j_addMajor_back;
    Button btn_j_addMajor_addMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_major);

        et_j_addMajor_majorName   = findViewById(R.id.et_v_addMajor_majorName);
        et_j_addMajor_majorPrefix = findViewById(R.id.et_v_addMajor_majorPrefix);
        btn_j_addMajor_back       = findViewById(R.id.btn_v_addMajor_back);
        btn_j_addMajor_addMajor   = findViewById(R.id.btn_v_addMajor_addMajor);

        backButtonClickListener();
        addMajorButtonClickListener();
    }

    private void backButtonClickListener()
    {
        btn_j_addMajor_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AddNewMajor.this, AddStudent.class));
            }
        });
    }

    private void addMajorButtonClickListener()
    {
        btn_j_addMajor_addMajor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }
}