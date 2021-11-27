package com.dkit.gd2.leannecreedon;


public class Passenger implements Comparable<Passenger> {

    /* Attributes */

    private String name;
    private int id;
    private String email;
    private String telephone;
    private PositionTracker homePosition;

    private IDSystem idSystem = IDSystem.getInstance("idSystem.txt");

    // Constructor

    public Passenger(String name, String email, String telephone, double homeLatitude, double homeLongitude) {
        this.name = name;
        this.id = idSystem.getNextId();
        this.email = email;
        this.telephone = telephone;
        this.homePosition = new PositionTracker(homeLatitude, homeLongitude);
    }

    public Passenger(String name, int id, String email, String telephone, double homeLatitude, double homeLongitude) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telephone = telephone;
        this.homePosition = new PositionTracker(homeLatitude, homeLongitude);
    }

    // Getters

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public PositionTracker getHomePosition() {
        return homePosition;
    }

    public static Passenger createNewPassenger(String name, int id, String email, String telephone, double latitude, double longitude)
    {
        return new Passenger(name, id, email, telephone, latitude, longitude);
    }


    // Implements Comparable Method

    @Override
    public int compareTo(Passenger otherPassenger)
    {
        return this.name.compareToIgnoreCase(otherPassenger.name);
    }

    // ToString Method

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", ID='" + id + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", homePosition=" + homePosition +
                '}';
    }

}
