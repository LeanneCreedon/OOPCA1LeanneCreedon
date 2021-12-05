package com.dkit.gd2.leannecreedon;

import java.util.ArrayList;

public class BookingService {

    private final ArrayList<Booking> bookingList;
    private Passengers passengers;
    private Vehicles vehicles;

    public BookingService(ArrayList<Booking> bookingList, Passengers passengers, Vehicles vehicles)
    {
        this.bookingList = bookingList;
        this.passengers = passengers;
        this.vehicles = vehicles;
    }

    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }

    public Passengers getPassengers() {
        return passengers;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return "BookingService{" +
                "bookingList=" + bookingList +
                ", passengers=" + passengers +
                ", vehicles=" + vehicles +
                '}';
    }
}
