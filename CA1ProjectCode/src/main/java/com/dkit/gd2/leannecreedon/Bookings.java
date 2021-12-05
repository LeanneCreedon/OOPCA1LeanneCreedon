package com.dkit.gd2.leannecreedon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bookings
{
        private ArrayList<Booking> bookingsList = new ArrayList<Booking>();

        public Bookings()
        {
            this.bookingsList = bookingsList;
        }

        public ArrayList<Booking> getBookingsList() {
            return bookingsList;
        }

        public void addNewBooking(Booking newBooking)
        {
            bookingsList.add(newBooking);
        }

        public boolean updateBooking(Booking oldBooking, Booking newBooking)
        {
            int foundPosition = findBooking(oldBooking);
            if(foundPosition < 0)
            {
                System.out.println(oldBooking.getBookingId() + ", was not found");
                return false;
            }
            this.bookingsList.set(foundPosition, newBooking);
            return true;
        }

        public boolean removeBooking(Booking booking)
        {
            int foundPosition = findBooking(booking);
            if(foundPosition < 0)
            {
                System.out.println(booking.getBookingId() + ", was not found");
                return false;
            }
            this.bookingsList.remove(foundPosition);
            System.out.println(booking.getBookingId() + ", was deleted");
            return true;
        }

        private int findBooking(Booking booking)
        {
            return this.bookingsList.indexOf(booking);
        }

        private int findBooking(int bookingID)
        {
            for (int i=0; i<this.bookingsList.size(); i++)
            {
                Booking booking = this.bookingsList.get(i);
                if(booking.getBookingId() == bookingID)
                {
                    return i;
                }
            }
            return -1;
        }

        public int searchBooking(Booking booking)
        {
            if(findBooking(booking) >= 0)
            {
                return booking.getBookingId();
            }
            return -1;
        }

        public Booking searchBooking(int bookingID)
        {
            int position = findBooking(bookingID);
            if(position >= 0)
            {
                return this.bookingsList.get(position);
            }
            return null;
        }

        public double calculatePrice(PositionTracker bookingPosStart, PositionTracker bookingPosEnd, PositionTracker vehicleDepotPos)
        {
            return calculateDistance1(bookingPosStart, vehicleDepotPos)+
                    calculateDistance2(bookingPosStart, bookingPosEnd)+
                    calculateDistance3(bookingPosEnd,vehicleDepotPos);
        }

        // Calculating the distances between the different positions
        public double calculateDistance1(PositionTracker bookingPosStart, PositionTracker vehicleDepotPos)
        {
            double dist1Lat = (bookingPosStart.getLatitude() - vehicleDepotPos.getLatitude());
            double dist1Long = (bookingPosStart.getLongitude() - vehicleDepotPos.getLongitude());

            return distanceCalculator(dist1Lat, dist1Long);
        }

        public double calculateDistance2(PositionTracker bookingPosStart, PositionTracker bookingPosEnd)
        {
            double dist2Lat = (bookingPosEnd.getLatitude() - bookingPosStart.getLatitude());
            double dist2Long = (bookingPosEnd.getLongitude() - bookingPosStart.getLongitude());

            return distanceCalculator(dist2Lat, dist2Long);
        }

        public double calculateDistance3(PositionTracker bookingPosEnd, PositionTracker vehicleDepotPos)
        {
            double dist3Lat = (vehicleDepotPos.getLatitude() - bookingPosEnd.getLatitude());
            double dist3Long = (vehicleDepotPos.getLongitude() - bookingPosEnd.getLongitude());

            return distanceCalculator(dist3Lat, dist3Long);
        }

        public double distanceCalculator(double distLat, double distLong)
        {
            double result = (distLat*distLat)+(distLong*distLong);
            return Math.sqrt(result);
        }

        // Printing the details of bookings

        //if ( number <= 80 && number >= 1 ) {
        //
        //    char myChar = '*';
        //
        //    // This while loop will run 15 times if value of number is 15..
        //    while(number > 0) {
        //        System.out.println(myChar);
        //        number--;   // Decrement the value of `number` by 1.
        //    }
        //}

        public void printBookingDetails()
        {
            System.out.println("Printing Booking Details: -\n");
            System.out.printf("%-10s%-20s%-40s%-45s%s%n", "ID", "Date Booked", "Start Position", "End Position", "Cost");
            for(int i=0; i<123; i++)
            {
                System.out.print('-');
            }
            for (Booking booking : bookingsList)
            {

                System.out.printf("%n%-10s%-20s%-40s%-45s%.2f%n", booking.getBookingId(),booking.getBookingDate(),
                        booking.getBookingStartPosition(),booking.getBookingEndPosition(),booking.getBookingCost());
            }

        }

}

