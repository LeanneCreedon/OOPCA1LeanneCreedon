package com.dkit.gd2.leannecreedon;

/**
 * Help from CA1 Starter code on Moodle
 */
public class PositionTracker {

    // Retrieves the position
    private double latitude;
    private double longitude;

    // Constructor
    public PositionTracker(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    // Setters
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // ToString
    @Override
    public String toString() {
        return "Latitude: " + latitude + ", Longitude: " + longitude;
    }
}
