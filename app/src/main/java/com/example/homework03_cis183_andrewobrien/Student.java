package com.example.homework03_cis183_andrewobrien;

public class Student
{
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private float gpa;
    private String major;

    public Student()
    {

    }

    public Student(String u, String f, String l, String e, int a, float g, String m)
    {
        username = u;
        firstName = f;
        lastName = l;
        email = e;
        age = a;
        gpa = g;
        major = m;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
