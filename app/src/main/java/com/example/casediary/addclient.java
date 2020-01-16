package com.example.casediary;

public class addclient {


    String clientId, clientname, address, city, zip, number;


    public addclient() {

    }

    public addclient(String clientId, String clientname, String address, String city, String zip, String number) {
        this.clientId = clientId;
        this.clientname = clientname;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.number = number;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientname() {
        return clientname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getNumber() {
        return number;
    }
}


