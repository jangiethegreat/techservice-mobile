package com.example.tech_service;


public class Constant {
    public static final String URL = "https://northsignalvillage.com/";
    public static final String HOME = URL + "api";
    public static final String LOGIN = HOME + "/login ";
    public static final String LOGOUT = HOME + "/logout";
    public static final String REGISTER = HOME + "/register";
    public static final String SAVE_USER_INFO = HOME + "/save_user_info";


    public static final String barangayid = HOME + "/mobile";
    public static final String ADD_BRGYID = barangayid + "/createBarangayid";
    public static final String SHOW_BRGYID = barangayid + "/show/{id}";
    public static final String EDT_BRGYID = barangayid + "/edit";


    public static final String scholarship = HOME + "/mobile";
    public static final String ADD_SCHOLARSHIP = scholarship + "/createScholarship";

    public static final String certificate = HOME + "/mobile";
    public static final String ADD_CERTIFICATE = certificate + "/createCertificate";

    public static final String scheduling = HOME + "/mobile";
    public static final String ADD_SCHEDULING = scheduling + "/createSchedulevaccine";

    public static final String complaint = HOME + "/mobile";
    public static final String ADD_COMPLAINT = complaint + "/createComplaint";

    public static final String UPDATE_BARANGAYID = barangayid+"/update";
    public static final String getAllBarangayids = barangayid+"/getAllBarangayids";


    public static final String ANNOUNCEMENTS =  HOME +"/announcement/all";

}
