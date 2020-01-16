package com.example.casediary;

public class AddCase {


    String caseId,clientname,casenumber,casetitle,courtno,cased,date;

    public AddCase(){

    }

    public AddCase(String caseId, String clientname, String casenumber, String casetitle, String courtno, String cased, String date) {
        this.caseId = caseId;
        this.clientname = clientname;
        this.casenumber = casenumber;
        this.casetitle = casetitle;
        this.courtno = courtno;
        this.cased = cased;
        this.date = date;
    }


    public String getCaseId() {
        return caseId;
    }

    public String getClientname() {
        return clientname;
    }

    public String getCasenumber() {
        return casenumber;
    }

    public String getCasetitle() {
        return casetitle;
    }

    public String getCourtno() {
        return courtno;
    }

    public String getCased() {
        return cased;
    }

    public String getDate() {
        return date;
    }
}
