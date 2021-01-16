package com.example.nomv20;

public class ProduceSample {
    private String produce;
    private int days;

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "ProduceSample{" +
                "produce='" + produce + '\'' + ", days=" + days +'}';
    }
}