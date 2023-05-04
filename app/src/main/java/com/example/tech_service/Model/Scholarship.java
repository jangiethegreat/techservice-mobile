package com.example.tech_service.Model;

public class Scholarship {
    private int id;
    private User user;
    private String scholarFname,
            scholarPhonenum,
            scholarSchool,
            scholarCourse,
            scholarEmail,
            user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return scholarFname;
    }
    public void setFname(String scholarFname) {
        this.scholarFname = scholarFname;
    }

    public String getPhonenum() {
        return scholarPhonenum;
    }
    public void setPhonenum(String scholarPhonenum) {
        this.scholarPhonenum = scholarPhonenum;
    }

    public String getSchool() {
        return scholarSchool;
    }
    public void setSchool(String scholarSchool) {
        this.scholarSchool = scholarSchool;
    }

    public String getCourse() {
        return scholarCourse;
    }
    public void setCourse(String scholarCourse) {
        this.scholarCourse = scholarCourse;
    }

    public String getEmail() {
        return scholarEmail;
    }
    public void setEmail(String scholarEmail) {
        this.scholarEmail = scholarEmail;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}

