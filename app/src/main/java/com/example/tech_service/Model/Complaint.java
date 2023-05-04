package com.example.tech_service.Model;


public class Complaint {
    private int id;
    private User user;
    private String complainantName,
            cAddressno,
            cStreet,
            cAddresszone,
            respondentName,
            respondentAge,
            rAddressno,
            rStreet,
            rAddresszone,
            complaintDesc,
            locationAddressno,
            locationStreet,
            complaintWhy,
            complainantPrayer,
            complaintDate,
            complainantAgrees;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getComplainantName() {
        return complainantName;
    }
    public void setComplainantName(String complainantName) {
        this.complainantName = complainantName;
    }

    public String getcAddressno() {
        return cAddressno;
    }
    public void setcAddressno(String cAddressno) {
        this.cAddressno = cAddressno;
    }

    public String getcStreet() {
        return cStreet;
    }
    public void setcStreet(String cStreet ) {
        this.cStreet = cStreet;
    }


    public String getcAddresszone() {
        return cAddresszone;
    }
    public void setcAddresszone(String cAddresszone) {
        this.cAddresszone = cAddresszone;
    }

    public String getrespondentName() {
        return respondentName;
    }
    public void setrespondentName(String respondentName) {
        this.respondentName = respondentName;
    }

    public String getrespondentAge() {
        return respondentAge;
    }
    public void setrespondentAge(String respondentAge) {
        this.respondentAge = respondentAge;
    }

    public String getrAddressno() {
        return rAddressno;
    }
    public void setrAddressno(String rAddressno) {
        this.rAddressno = rAddressno;
    }

    public String getrStreet() {
        return rStreet;
    }
    public void setrStreet(String rStreet) {
        this.rStreet = rStreet;
    }

    public String getrAddresszone() {
        return rAddresszone;
    }
    public void setrAddresszone(String rAddresszone) {
        this.rAddresszone = rAddresszone;
    }

    public String getcomplaintDesc() {
        return complaintDesc;
    }
    public void setcomplaintDesc(String complaintDesc) {
        this.complaintDesc = complaintDesc;
    }

    public String getlocationAddressno() {
        return locationAddressno;
    }
    public void setlocationAddressno(String locationAddressno) {
        this.locationAddressno = locationAddressno;
    }

    public String getlocationStreet() {
        return locationStreet;
    }
    public void setlocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String getcomplaintWhy() {
        return complaintWhy;
    }
    public void setcomplaintWhy(String complaintWhy) {
        this.complaintWhy = complaintWhy;
    }

    public String getcomplainantPrayer() {
        return complainantPrayer;
    }
    public void setcomplainantPrayer(String complainantPrayer) {
        this.complainantPrayer = complainantPrayer;
    }

    public String getcomplaintDate() {
        return complaintDate;
    }
    public void setcomplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }


    public String getcomplainantAgrees() {
        return complainantAgrees;
    }
    public void setcomplainantAgrees(String complainantAgrees) {
        this.complainantAgrees = complainantAgrees;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
