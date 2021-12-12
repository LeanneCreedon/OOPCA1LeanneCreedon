package com.dkit.gd2.leannecreedon;

import java.util.Comparator;

public class CompareByDateTime implements Comparator<Booking>{

    // Comparing two bookings by the date so that I can sort the bookings by
    // date/time order.
    @Override
    public int compare(Booking booking1, Booking booking2)
    {
        return booking2.getBookingDate().compareTo(booking1.getBookingDate());
    }
}
