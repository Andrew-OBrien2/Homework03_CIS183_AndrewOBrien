package com.example.homework03_cis183_andrewobrien;

public class SessionData
{

    private static Student loggedInUser;

    public static Student getRetreivedStudent()
    {
        return loggedInUser;
    }

    public static void setRetrievedStudent(Student s)
    {
        loggedInUser = s;
    }

}
