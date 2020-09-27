package com.example.mapappwork01;

public class Restaurants {

    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private String photo;


    public String getName() {
        return name;
    }

    public void setName(String restaurant) {
        this.name = restaurant;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
