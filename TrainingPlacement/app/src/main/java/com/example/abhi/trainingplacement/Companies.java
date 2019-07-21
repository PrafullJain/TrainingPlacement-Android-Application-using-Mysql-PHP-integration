package com.example.abhi.trainingplacement;

public class Companies {

    private int company_id,company_post;
    private String company_name,company_detail,company_venue,company_vacancy,
            company_vision,company_mission,date,trash,gpa,twelveth,tenth;

    public int getCompany_id() {
        return company_id;
    }

    public int getCompany_post() {
        return company_post;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_detail() {
        return company_detail;
    }


    public String getCompany_venue() {
        return company_venue;
    }

    public String getCompany_vacancy() {
        return company_vacancy;
    }

    public String getCompany_vision() {
        return company_vision;
    }

    public String getCompany_mission() {
        return company_mission;
    }

    public Companies(int company_id, int company_post, String company_name, String company_detail,
                     String company_venue, String company_vacancy, String company_vision, String company_mission,
                     String date, String trash,String gpa,String twelveth,String tenth) {
        this.company_id = company_id;
        this.company_post = company_post;
        this.company_name = company_name;
        this.company_detail = company_detail;
        this.company_venue = company_venue;
        this.company_vacancy = company_vacancy;
        this.company_vision = company_vision;
        this.company_mission = company_mission;
        this.date = date;
        this.trash = trash;
        this.gpa=gpa;
        this.twelveth=twelveth;
        this.tenth=tenth;

    }

    public String getTrash() {
        return trash;
    }

    public void setTrash(String trash) {
        this.trash = trash;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTenth() {
        return tenth;
    }

    public void setTenth(String tenth) {
        this.tenth = tenth;
    }

    public String getTwelveth() {
        return twelveth;
    }

    public void setTwelveth(String twelveth) {
        this.twelveth = twelveth;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
}

