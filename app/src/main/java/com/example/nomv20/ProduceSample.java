package com.example.nomv20;

import android.os.Parcel;
import android.os.Parcelable;

public class ProduceSample implements Parcelable {
    private String produce;
    private int days;

    protected ProduceSample(Parcel in) {
        produce = in.readString();
        days = in.readInt();
    }

    public ProduceSample() {

    }

    public static final Creator<ProduceSample> CREATOR = new Creator<ProduceSample>() {
        @Override
        public ProduceSample createFromParcel(Parcel in) {
            return new ProduceSample(in);
        }

        @Override
        public ProduceSample[] newArray(int size) {
            return new ProduceSample[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(produce);
        dest.writeInt(days);

    }
}