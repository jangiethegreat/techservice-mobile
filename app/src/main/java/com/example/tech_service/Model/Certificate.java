package com.example.tech_service.Model;

public class Certificate {
    private int id;
    private User user;
    private String purpose, isRegistered, havePendingCase, name, gender, birthdate, p_birth, nationality, contact_num, c_status, addressNo, street,
            addressZone, provincialAddress, yearLiving, areYouSure;

    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getIsRegistered() { return isRegistered; }
    public void setIsRegistered(String isRegistered) { this.isRegistered = isRegistered; }

    public String getHavePendingCase() { return havePendingCase; }
    public void setHavePendingCase(String havePendingCase) { this.havePendingCase = havePendingCase; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender ) { this.gender = gender; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public String getP_birth() { return p_birth ; }
    public void setP_birth(String p_birth) { this.p_birth = p_birth; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getContact_num() { return contact_num; }
    public void setContact_num(String contact_num) { this.contact_num = contact_num; }

    public String getC_status() { return c_status; }
    public void setC_status(String c_status) { this.c_status = c_status; }

    public String getAddressNo() { return addressNo; }
    public void setAddressNo(String addressNo) { this.addressNo = addressNo; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getAddressZone() { return addressZone; }
    public void setAddressZone(String addressZone) { this.addressZone = addressZone; }

    public String getProvincialAddress() { return provincialAddress; }
    public void setProvincialAddress(String provincialAddress) { this.provincialAddress = provincialAddress; }

    public String getYearLiving() { return yearLiving; }
    public void setYearLiving(String yearLiving) { this.yearLiving = yearLiving; }

    public String getAreYouSure() { return areYouSure; }
    public void setAreYouSure(String areYouSure) { this.areYouSure = areYouSure; }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
