package com.example.nomv20;

import java.util.Date;

public class Vegetable {
    private String name;
    private Date expirationDate;
    private Date enteredDate;
    boolean expired;


    public Vegetable(String n, Date expDate, Date entDate) {
        name = n;
        expirationDate = expDate;
        enteredDate = entDate;
    }

    public Vegetable(String n, Date expDate, Date entDate, boolean expired) {

    }

    public Vegetable() {

    }


    public String getName() {
        return name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setExpired(boolean b) {
        expired = b;
    }


}
