package com.dkit.gd2.leannecreedon;

import java.util.ArrayList;

public class BookingService {

    private final ArrayList<VehicleBookings> bookingList;
    private PassengerInfo passengerInfo;
    private VehicleManager vehicleManager;

    public BookingService(ArrayList<VehicleBookings> bookingList, PassengerInfo passengerInfo, VehicleManager vehicleManager)
    {
        this.bookingList = bookingList;
        this.passengerInfo = passengerInfo;
        this.vehicleManager = vehicleManager;
    }
}
