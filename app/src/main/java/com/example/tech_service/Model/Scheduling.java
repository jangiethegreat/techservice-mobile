package com.example.tech_service.Model;

public class Scheduling {
        private int id;
        private User user;
        private String name, contact_num, vaccine,
                date, time, addressNo, street, email;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getContact_num() {
            return contact_num;
        }
        public void setContact_num(String contact_num) {
            this.contact_num = contact_num;
        }

        public String getVaccine() {
            return vaccine;
        }
        public void setVaccine(String vaccine) {
            this.vaccine = vaccine;
        }

        public String getDate() {
            return date;
        }
        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }

        public String getAddressNo() {
            return addressNo;
        }
        public void setAddressNo(String addressNo) {
            this.addressNo = addressNo;
        }

        public String getStreet() {
            return street;
        }
        public void setStreet(String street) {
            this.street = street;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

    }

