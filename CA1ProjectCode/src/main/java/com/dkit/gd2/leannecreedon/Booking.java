package com.dkit.gd2.leannecreedon;

import java.time.LocalDate;


public class Booking  {

    private IDSystem idSystem = IDSystem.getInstance("idSystem.txt");

    /* Attributes */

    private int passengerId;
    private int vehicleId;
    private int bookingId;
    private LocalDate bookingDate;
    private PositionTracker bookingStartPosition;
    private PositionTracker bookingEndPosition;
    private double bookingCost;

    // Constructor

    public Booking(int passengerId, int vehicleId, IDSystem bookingId, LocalDate bookingDate, PositionTracker bookingStartPosition,
                   PositionTracker bookingEndPosition, double bookingCost)
    {
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingId = bookingId.getNextId();
        this.bookingDate = bookingDate;
        this.bookingStartPosition = bookingStartPosition;
        this.bookingEndPosition = bookingEndPosition;
        this.bookingCost = bookingCost;
    }

    public Booking(int passengerId, int vehicleId, int bookingId, LocalDate bookingDate, PositionTracker bookingStartPosition,
                   PositionTracker bookingEndPosition, double bookingCost)
    {
        this.passengerId = passengerId;
        this.vehicleId = vehicleId;
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingStartPosition = bookingStartPosition;
        this.bookingEndPosition = bookingEndPosition;
        this.bookingCost = bookingCost;
    }

    public Booking(int bookingId, LocalDate bookingDate, PositionTracker bookingStartPosition,
                   PositionTracker bookingEndPosition, double bookingCost)
    {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingStartPosition = bookingStartPosition;
        this.bookingEndPosition = bookingEndPosition;
        this.bookingCost = bookingCost;
    }


    // Getters

    public IDSystem getIdSystem() {
        return idSystem;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getVehicleId()
    {
        return vehicleId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
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

    public static Booking createNewBooking(int passengerID, int vehicleID, IDSystem bookingID,
                                           LocalDate bookingDate, PositionTracker bookingStartPosition,
                                           PositionTracker bookingEndPosition, double bookingCost)
    {
        return new Booking(passengerID, vehicleID, bookingID, bookingDate, bookingStartPosition, bookingEndPosition, bookingCost);
    }

    public static Booking updateBooking(int bookingID, LocalDate bookingDate, PositionTracker bookingStartPosition,
                                           PositionTracker bookingEndPosition, double bookingCost)
    {
        return new Booking(bookingID, bookingDate, bookingStartPosition, bookingEndPosition, bookingCost);
    }


    // ToString Method


    @Override
    public String toString() {
        return "Booking{" +
                "idSystem=" + idSystem +
                ", bookingId=" + bookingId +
                ", passengerId=" + passengerId +
                ", vehicleId=" + vehicleId +
                ", bookingDate=" + bookingDate +
                ", bookingStartPosition=" + bookingStartPosition +
                ", bookingEndPosition=" + bookingEndPosition +
                ", bookingCost=" + bookingCost +
                '}';
    }

}
