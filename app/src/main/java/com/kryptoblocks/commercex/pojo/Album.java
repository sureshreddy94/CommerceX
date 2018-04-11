package com.kryptoblocks.commercex.pojo;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Album {
    private String remove;
    private String cost;
    private String name;
    private int thumbnail;

    public Album(String bakasur, String s, int cover) {
    }

    public Album(String name, String cost, String remove, int thumbnail) {
        this.name = name;
        this.cost = cost;
        this.remove = remove;
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

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}

