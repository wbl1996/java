package com.wang.consumer.entities;

public class Ticket {
    private int id;
    private String place;
    private String time;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Ticket(int id, String place, String time, int price) {
        this.id = id;
        this.place = place;
        this.time = time;
        this.price = price;
    }
    public Ticket(){}
}
