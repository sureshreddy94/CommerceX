package com.kryptoblocks.commercex.pojo;

/**
 * Created by Admin on 06-03-2018.
 */

public class HomePro_Details{
    private String cost;
    private String name;
    private int thumbnail;

    public HomePro_Details() {

    }

    public HomePro_Details(String name, String cost, int thumbnail) {
        this.name = name;
        this.cost = cost;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}