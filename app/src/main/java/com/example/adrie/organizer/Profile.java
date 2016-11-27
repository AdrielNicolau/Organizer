package com.example.adrie.organizer;



/**
 * Created by adrie on 27/11/2016.
 */

public class Profile {
    private String mName;
    private String mStudentNumber;
    private String mEmail;

    public Profile(String name, String studentNumber, String email) {
        mName = name;
        mStudentNumber = studentNumber;
        mEmail = email;
    }

    public Profile() {

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStudentNumber() {
        return mStudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        mStudentNumber = studentNumber;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
