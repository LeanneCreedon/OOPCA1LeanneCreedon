package com.dkit.gd2.leannecreedon;

public class VehicleBookings {

    /* Attributes */

    private String bookingID;
    private String bookingDateAndTime;
    private double bookingStartLatitude;
    private double bookingStartLongitude;
    private double bookingEndLatitude;
    private double bookingEndLongitude;
    private double bookingCost;

    // Constructor

    public VehicleBookings(String bookingID, String bookingDateAndTime, double bookingStartLatitude,
                           double bookingStartLongitude, double bookingEndLatitude,
                           double bookingEndLongitude, double bookingCost)
    {
        this.bookingID = bookingID;
        this.bookingDateAndTime = bookingDateAndTime;
        this.bookingStartLatitude = bookingStartLatitude;
        this.bookingStartLongitude = bookingStartLongitude;
        this.bookingEndLatitude = bookingEndLatitude;
        this.bookingEndLongitude = bookingEndLongitude;
        this.bookingCost = bookingCost;
    }

    // Getters

    public String getBookingID() {
        return bookingID;
    }

    public String getBookingDateAndTime() {
        return bookingDateAndTime;
    }

    public double getBookingStartLatitude() {
        return bookingStartLatitude;
    }

    public double getBookingStartLongitude() {
        return bookingStartLongitude;
    }

    public double getBookingEndLatitude() {
        return bookingEndLatitude;
    }

    public double getBookingEndLongitude() {
        return bookingEndLongitude;
    }

    public double getBookingCost() {
        return bookingCost;
    }

    @Override
    public String toString() {
        return "VehicleBookings{" +
                "bookingID='" + bookingID + '\'' +
                ", bookingDateAndTime='" + bookingDateAndTime + '\'' +
                ", bookingStartLatitude=" + bookingStartLatitude +
                ", bookingStartLongitude=" + bookingStartLongitude +
                ", bookingEndLatitude=" + bookingEndLatitude +
                ", bookingEndLongitude=" + bookingEndLongitude +
                ", bookingCost=" + bookingCost +
                '}';
    }
}
