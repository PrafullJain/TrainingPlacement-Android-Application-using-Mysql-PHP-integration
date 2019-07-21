package com.example.abhi.trainingplacement;

public class Api {
    private static final String ROOT_URL ="http://192.168.43.42:8080/TrainingPlacement/v1/Api.php?apicall=";

    public static final String URL_CREATE_COMPANY = ROOT_URL + "createCompany";
    public static final String URL_READ_COMPANY = ROOT_URL + "getCompanies";
    public static final String URL_UPDATE_COMPANY = ROOT_URL + "updateCompany";
    public static final String URL_DELETE_COMPANY = ROOT_URL + "deleteCompany&id=";
    public static final String URL_READRAND_COMPANY = ROOT_URL + "getCompany&company_name=";

    public static final String URL_CREATE_STUDENT = ROOT_URL + "createStudent";
    public static final String URL_READ_STUDENT = ROOT_URL + "getStudents";
    public static final String URL_UPDATE_STUDENT = ROOT_URL + "updateStudent";
    public static final String URL_DELETE_STUDENT = ROOT_URL + "deleteStudent&name=";
    public static final String URL_READRAND_STUDENT = ROOT_URL + "getStudent&email=";
}
