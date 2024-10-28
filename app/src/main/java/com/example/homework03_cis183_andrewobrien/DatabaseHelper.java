package com.example.homework03_cis183_andrewobrien;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "StudentData.db";
    private static final String students_table_name = "Students";
    private static final String majors_table_name = "Majors";

    public DatabaseHelper(Context c)
    {
        super(c,database_name,null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create the tables

        //students table
        db.execSQL("CREATE TABLE " + students_table_name + " (" + "username varchar(50) primary key not null, " + "firstName varchar(50), " + "lastName varchar(50), " + "email varchar(50), " + "age integer, " + "gpa float, " + "major varchar(50));");

        //majors table
        db.execSQL("CREATE TABLE " + majors_table_name + " (" + "majorId integer primary key autoincrement not null, " + "majorName varchar(50), " + "majorPrefix varchar(4));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + students_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + majors_table_name + ";");

        onCreate(db);
    }

    public String getStudentsDbName()
    {
        return students_table_name;
    }

    public String getMajorsDbName()
    {
        return majors_table_name;
    }

    public void initAllTables()
    {
        initStudents();
        initMajors();
    }

    private void initStudents()
    {
        //ensure data is only added once
        if(countRecordsFromTable(students_table_name) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            //dummy data
            db.execSQL("INSERT INTO " + students_table_name + "(username, firstName, lastName, email, age, gpa, major) VALUES ('jdoe', 'John', 'Doe', 'jdoe@university.edu', 20, 3.5, 'CIS');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, firstName, lastName, email, age, gpa, major) VALUES ('asmith', 'Alice', 'Smith', 'asmith@university.edu', 22, 3.8, 'BUS');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, firstName, lastName, email, age, gpa, major) VALUES ('btaylor', 'Bob', 'Taylor', 'btaylor@college.edu', 19, 2.9, 'ART');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, firstName, lastName, email, age, gpa, major) VALUES ('clarkson', 'Charlie', 'Clarkson', 'cclarkson@university.edu', 21, 3.2, 'CIS');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, firstName, lastName, email, age, gpa, major) VALUES ('edavis', 'Emma', 'Davis', 'edavis@college.edu', 23, 3.6, 'BIO');");
            db.execSQL("INSERT INTO " + students_table_name + "(username, firstName, lastName, email, age, gpa, major) VALUES ('fwright', 'Frank', 'Wright', 'fwright@university.edu', 20, 2.7, 'BUS');");

            db.close();
        }
    }

    private void initMajors()
    {
        //ensure data is only added once
        if(countRecordsFromTable(majors_table_name) == 0)
        {

            SQLiteDatabase db = this.getWritableDatabase();

            //dummy data
            db.execSQL("INSERT INTO " + majors_table_name + "(majorID, majorName, majorPrefix) VALUES ('1', 'Computer Information Systems', 'CIS');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorID, majorName, majorPrefix) VALUES ('2', 'Business Administration', 'BUS');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorID, majorName, majorPrefix) VALUES ('3', 'Fine Arts', 'ART');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorID, majorName, majorPrefix) VALUES ('4', 'Biology', 'BIO');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorID, majorName, majorPrefix) VALUES ('5', 'Psychology', 'PSY');");


            db.close();
        }
    }

    public int countRecordsFromTable(String tableName)
    {
        //get an instance of a readable database
        //we only need readable because we are not adding anything to the database with this action
        SQLiteDatabase db = this.getReadableDatabase();

        //count the number of entries in the table that was passed to the function
        //this is a built-in function1
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        //whenever we open the database we need to close it.
        db.close();

        return numRows;
    }

    public boolean usernameExists(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        // I want to count the number of records that contain the username that was passed to us
        // The only two viable values for the count query to return are:
        // 1 - the username was found
        // 0 - the username was not found
        // This ensures that no two people can have the same username, as it is the primary key
        String checkUsername = "SELECT count(username) FROM " + students_table_name + " WHERE username = '" + username + "';";

        // Run the query
        Cursor cursor = db.rawQuery(checkUsername, null);

        cursor.moveToFirst();

        int count = cursor.getInt(0);

        db.close();

        //if the count is not zero, the username was found
        if (count != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void getAllStudentDataGivenUsername(String username)
    {
        //retrieve all student information based on the provided username
        Student retrievedStudent = null;

        //check if the student exists with the given username
        if (usernameExists(username))
        {
            retrievedStudent = new Student();

            String selectQuery = "SELECT * FROM " + students_table_name + " WHERE username = '" + username + "';";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor != null) {
                cursor.moveToFirst();

                //populate the Student object with data from the cursor
                retrievedStudent.setUsername(cursor.getString(0));
                retrievedStudent.setFirstName(cursor.getString(1));
                retrievedStudent.setLastName(cursor.getString(2));
                retrievedStudent.setEmail(cursor.getString(3));
                retrievedStudent.setAge(cursor.getInt(4));
                retrievedStudent.setGpa(cursor.getFloat(5));
                retrievedStudent.setMajor(cursor.getString(6));

                //set the retrieved student in SessionData
                SessionData.setRetrievedStudent(retrievedStudent);
            }

            cursor.close();
            db.close();
        }
        else
        {
            //if username does not exist, handle error
            SessionData.setRetrievedStudent(null);
            Log.d("Error", "Username not found");
        }
    }

    public void addStudentToDB(Student s)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + students_table_name + " (username, firstName, lastName, email, age, gpa, major) VALUES ('" + s.getUsername() + "','" + s.getFirstName() + "','" + s.getLastName() + "','" + s.getEmail() + "'," + s.getAge() + "," + s.getGpa() + ",'" + s.getMajor() + "');");

        db.close();
    }

    public void deleteStudentFromDB(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + students_table_name + " WHERE username = '" + username + "';");

        db.close();
    }

    public ArrayList<Student> getAllStudents()
    {
        String selectStatement = "SELECT * FROM " + students_table_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectStatement, null);
        ArrayList<Student> students = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do
            {
                String username = cursor.getString(0);
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                String email = cursor.getString(3);
                int age = cursor.getInt(4);
                float gpa = cursor.getFloat(5);
                String major = cursor.getString(6);

                students.add(new Student(username, firstName, lastName, email, age, gpa, major));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return students;
    }

    public ArrayList<String> getAllMajors()
    {
        ArrayList<String> majors = new ArrayList<>();
        String selectStatement = "SELECT majorName FROM " + majors_table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement, null);

        if (cursor.moveToFirst()) {
            do {
                String major = cursor.getString(0);
                majors.add(major);
            } while (cursor.moveToNext());
        }

        db.close();
        return majors;
    }

}
