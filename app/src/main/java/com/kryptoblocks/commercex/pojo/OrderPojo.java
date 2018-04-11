package com.kryptoblocks.commercex.pojo;

/**
 * Created by Admin on 19-02-2018.
 */

public class OrderPojo {
    private String Devlivry_status;
    private String name;
    private int thumbnail;

    public OrderPojo() {
    }

    public OrderPojo(String name, String Devlivry_status, int thumbnail) {
        this.name = name;
        this.Devlivry_status = Devlivry_status;
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

    public String getDevlivry_status() {
        return Devlivry_status;
    }

    public void setDevlivry_status(String cost) {
        this.Devlivry_status = cost;
    }
}

