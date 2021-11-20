package com.dkit.gd2.leannecreedon;

public class Passenger {

    /* Attributes */

    private String name;
    private String ID;
    private String email;
    private String telephone;
    private double homeLatitude;
    private double homeLongitude;

    // Constructor

    public Passenger(String name, String ID, String email, String telephone, double homeLatitude, double homeLongitude)
    {
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.telephone = telephone;
        this.homeLatitude = homeLatitude;
        this.homeLongitude = homeLongitude;
    }

    // Getters

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public double getHomeLatitude() {
        return homeLatitude;
    }

    public double getHomeLongitude() {
        return homeLongitude;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", homeLatitude=" + homeLatitude +
                ", homeLongitude=" + homeLongitude +
                '}';
    }
}
