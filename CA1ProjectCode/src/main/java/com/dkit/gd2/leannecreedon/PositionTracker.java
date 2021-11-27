package com.dkit.gd2.leannecreedon;

public class PositionTracker {

    // Retrieves the position
    private double latitude;
    private double longitude;

    public PositionTracker(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PositionTracker{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
