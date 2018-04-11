package com.kryptoblocks.commercex.api_pojo;

import java.util.List;

public class Location {

    private String type;
    private List<Integer> coordinates = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

}
