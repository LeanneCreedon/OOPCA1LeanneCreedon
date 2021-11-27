package com.dkit.gd2.leannecreedon;

public class VehicleBookings {

    /* Attributes */

    private String bookingID;
    private String bookingDateAndTime;
    private PositionTracker bookingStartPosition;
    private PositionTracker bookingEndPosition;
    private double bookingCost;

    // Constructor

    public VehicleBookings(String bookingID, String bookingDateAndTime, double bookingStartLatitude,
                           double bookingStartLongitude, double bookingEndLatitude,
                           double bookingEndLongitude, double bookingCost)
    {
        this.bookingID = bookingID;
        this.bookingDateAndTime = bookingDateAndTime;
        this.bookingStartPosition = new PositionTracker(bookingStartLatitude,bookingStartLongitude);
        this.bookingEndPosition = new PositionTracker(bookingEndLatitude,bookingEndLongitude);
        this.bookingCost = bookingCost;
    }

    // Getters

    public String getBookingID() {
        return bookingID;
    }

    public String getBookingDateAndTime() {
        return bookingDateAndTime;
    }

    public PositionTracker getBookingStartPosition() {
        return bookingStartPosition;
    }

    public PositionTracker getBookingEndPosition() {
        return bookingEndPosition;
    }

    public double getBookingCost() {
        return bookingCost;
    }


    // ToString Method

    @Override
    public String toString() {
        return "VehicleBookings{" +
                "bookingID='" + bookingID + '\'' +
                ", bookingDateAndTime='" + bookingDateAndTime + '\'' +
                ", bookingStartPosition=" + bookingStartPosition +
                ", bookingStartPosition=" + bookingEndPosition +
                ", bookingCost=" + bookingCost +
                '}';
    }
}
