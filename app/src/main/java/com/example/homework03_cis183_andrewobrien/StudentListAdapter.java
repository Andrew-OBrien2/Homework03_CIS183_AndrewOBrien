package com.example.homework03_cis183_andrewobrien;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homework03_cis183_andrewobrien.Student;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Student> listOfStudents;

    public StudentListAdapter(Context c, ArrayList<Student> ls)
    {
        context = c;
        listOfStudents = ls;
    }

    @Override
    public int getCount()
    {
        return listOfStudents.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfStudents.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.listview_cell, null);
        }

        //connect GUI
        TextView studentUsername = view.findViewById(R.id.tv_v_cc_username);
        TextView studentFirstName = view.findViewById(R.id.tv_v_cc_firstname);
        TextView studentLastName = view.findViewById(R.id.tv_v_cc_lastname);

        //get data at this specific location from the list of students
        Student student = listOfStudents.get(i);

        // Set the GUI for the custom cell
        studentFirstName.setText(student.getFirstName());
        studentUsername.setText(student.getUsername());
        studentLastName.setText(student.getLastName());

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, StudentDetails.class);
                intent.putExtra("username", student.getUsername());
                intent.putExtra("firstName", student.getFirstName());
                intent.putExtra("lastName", student.getLastName());
                intent.putExtra("email", student.getEmail());
                intent.putExtra("age", student.getAge());
                intent.putExtra("gpa", student.getGpa());
                intent.putExtra("major", student.getMajor());
                context.startActivity(intent);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Delete the student from the database directly
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.deleteStudentFromDB(student.getUsername()); // Assuming you have a method to delete by username

                // Remove the student from the list and notify the adapter
                listOfStudents.remove(i);
                notifyDataSetChanged();

                return true; // Indicate that the long click was handled
            }
        });

        // Return the view for the list item
        return view;
    }

    public void clear()
    {
        listOfStudents.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Student> newStudents)
    {
        listOfStudents.addAll(newStudents);
        notifyDataSetChanged();
    }


}