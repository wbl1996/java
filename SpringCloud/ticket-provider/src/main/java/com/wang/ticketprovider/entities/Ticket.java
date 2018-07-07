package com.wang.ticketprovider.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="ticket_info")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    private int id;
    @Column
    private String place;
    @Column
    private String time;
    @Column
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

    public void setTime(String date) {
        this.time = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Ticket(int id, String place, String date, int price) {
        this.id = id;
        this.place = place;
        this.time = date;
        this.price = price;
    }
    public Ticket(){}
}
