package com.example.homework03_cis183_andrewobrien;

public class Major
{
    private String majorID;
    private String majorName;
    private String majorPrefix;

    public Major()
    {

    }

    public Major(String id, String n, String p)
    {
        majorID = id;
        majorName = n;
        majorPrefix = p;
    }

    public String getMajorID() {
        return majorID;
    }

    public void setMajorID(String majorID) {
        this.majorID = majorID;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorPrefix() {
        return majorPrefix;
    }

    public void setMajorPrefix(String majorPrefix) {
        this.majorPrefix = majorPrefix;
    }
}

