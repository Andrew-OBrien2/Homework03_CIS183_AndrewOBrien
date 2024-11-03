package com.example.homework03_cis183_andrewobrien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchListAdapter extends BaseAdapter
{
     Context context;
     ArrayList<Student> listOfStudents; // List of students as strings
     DatabaseHelper dbHelper;

    public SearchListAdapter(Context c, ArrayList<Student> students, DatabaseHelper dbHelper) {
        context = c;
        listOfStudents = students;
        this.dbHelper = dbHelper;
    }

    @Override
    public int getCount() {
        return listOfStudents.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfStudents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.search_listview_cell, null);
        }

        //connect GUI
        TextView studentUsername  = view.findViewById(R.id.tv_v_cc_search_username);
        TextView studentFirstName = view.findViewById(R.id.tv_v_cc_search_firstname);
        TextView studentLastName  = view.findViewById(R.id.tv_v_cc_search_lastname);
        TextView studentEmail     = view.findViewById(R.id.tv_v_cc_search_email);
        TextView studentAge       = view.findViewById(R.id.tv_v_cc_search_age);
        TextView studentGPA       = view.findViewById(R.id.tv_v_cc_search_gpa);
        TextView studentMajor     = view.findViewById(R.id.tv_v_cc_search_major);


        // Get the current student
        Student currentStudent = listOfStudents.get(i);

        // Set the values in the TextViews
        studentUsername.setText(currentStudent.getUsername());
        studentFirstName.setText(currentStudent.getFirstName());
        studentLastName.setText(currentStudent.getLastName());
        studentEmail.setText(currentStudent.getEmail());
        studentAge.setText(String.valueOf(currentStudent.getAge()));
        studentGPA.setText(String.valueOf(currentStudent.getGpa()));
        studentMajor.setText(dbHelper.getMajorPrefix(currentStudent.getMajor()));

        return view;
    }
}
