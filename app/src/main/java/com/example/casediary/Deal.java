package com.example.casediary;

public class Deal {
    private String address;
    private String city;
    private String clientname,number,zip;

    public Deal() {
    }

    public Deal(String address, String city, String clientname, String number, String zip) {
        this.address = address;
        this.city = city;
        this.clientname = clientname;
        this.number = number;
        this.zip = zip;
    }


    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getClientname() {
        return clientname;
    }

    public String getNumber() {
        return number;
    }

    public String getZip() {
        return zip;
    }
}
