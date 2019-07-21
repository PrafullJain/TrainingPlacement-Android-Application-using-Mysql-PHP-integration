package com.example.abhi.trainingplacement;

public class Student
{
    private String name,email,registration_no,mob_no,alt_mob_no,password,gpa,twelveth,tenth;

    public Student(String name, String email, String registration_no, String mob_no,
                   String alt_mob_no, String password, String gpa, String twelveth, String tenth) {
        this.name = name;
        this.email = email;
        this.registration_no = registration_no;
        this.mob_no = mob_no;
        this.alt_mob_no = alt_mob_no;
        this.password = password;
        this.gpa = gpa;
        this.twelveth = twelveth;
        this.tenth = tenth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getAlt_mob_no() {
        return alt_mob_no;
    }

    public void setAlt_mob_no(String alt_mob_no) {
        this.alt_mob_no = alt_mob_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getTwelveth() {
        return twelveth;
    }

    public void setTwelveth(String twelveth) {
        this.twelveth = twelveth;
    }

    public String getTenth() {
        return tenth;
    }

    public void setTenth(String tenth) {
        this.tenth = tenth;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }
}
