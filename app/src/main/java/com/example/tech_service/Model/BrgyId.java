package com.example.tech_service.Model;


import java.util.Date;

public class BrgyId {
    private int addressNo, id, addressZone, user_id , typeofrequest_id =1
            , price ;
    private User user;

    private String fullname,
            date,
            contactno,
            street,
            precintNo,
            contactperson,
            guardianContact,
            guardianAddress;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTypeofrequest_id() {
        return typeofrequest_id;
    }

    public void setTypeofrequest_id(int typeofrequest_id) {
        this.typeofrequest_id = typeofrequest_id;
    }

    public int getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(int addressNo) {
        this.addressNo = addressNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressZone() {
        return addressZone;
    }

    public void setAddressZone(int addressZone) {
        this.addressZone = addressZone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPrecintNo() {
        return precintNo;
    }

    public void setPrecintNo(String precintNo) {
        this.precintNo = precintNo;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public void setGuardianContact(String guardianContact) {
        this.guardianContact = guardianContact;
    }

    public String getGuardianAddress() {
        return guardianAddress;
    }

    public void setGuardianAddress(String guardianAddress) {
        this.guardianAddress = guardianAddress;
    }
}