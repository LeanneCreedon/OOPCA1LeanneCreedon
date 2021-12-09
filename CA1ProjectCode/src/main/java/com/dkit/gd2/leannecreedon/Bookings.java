package com.dkit.gd2.leannecreedon;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Bookings
{
        private ArrayList<Booking> bookingsList = new ArrayList<Booking>();
        private Passengers passengers;
        private Vehicles vehicles;

        public Bookings() {
            this.bookingsList = new ArrayList<>();
        }

        public Bookings(Passengers passengers, Vehicles vehicles) {
            this.bookingsList = new ArrayList<>();
            this.passengers = passengers;
            this.vehicles = vehicles;
        }

        public ArrayList<Booking> getBookingsList() {
            return bookingsList;
        }

        public boolean addNewBooking(Booking newBooking)
        {
            if(findBooking(newBooking.getVehicleId()) >= 0)
            {
                return true;
            }
            bookingsList.add(newBooking);
            return false;
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
        private double calculateDistance1(PositionTracker bookingPosStart, PositionTracker vehicleDepotPos)
        {
            double dist1Lat = (bookingPosStart.getLatitude() - vehicleDepotPos.getLatitude());
            double dist1Long = (bookingPosStart.getLongitude() - vehicleDepotPos.getLongitude());

            return distanceCalculator(dist1Lat, dist1Long);
        }

        private double calculateDistance2(PositionTracker bookingPosStart, PositionTracker bookingPosEnd)
        {
            double dist2Lat = (bookingPosEnd.getLatitude() - bookingPosStart.getLatitude());
            double dist2Long = (bookingPosEnd.getLongitude() - bookingPosStart.getLongitude());

            return distanceCalculator(dist2Lat, dist2Long);
        }

        private double calculateDistance3(PositionTracker bookingPosEnd, PositionTracker vehicleDepotPos)
        {
            double dist3Lat = (vehicleDepotPos.getLatitude() - bookingPosEnd.getLatitude());
            double dist3Long = (vehicleDepotPos.getLongitude() - bookingPosEnd.getLongitude());

            return distanceCalculator(dist3Lat, dist3Long);
        }

        private double distanceCalculator(double distLat, double distLong)
        {
            double result = (distLat*distLat)+(distLong*distLong);
            return Math.sqrt(result);
        }

        // Printing the details of bookings

        private void printHeading()
        {
            System.out.printf("%-15s%-15s%-15s%-20s%-40s%-45s%s%n", "BookingID", "PassengerID", "VehicleID",
                    "Date Booked", "Start Position", "End Position", "Cost");
            for(int i=0; i<170; i++)
            {
                System.out.print('-');
            }
        }

        public void printBookingDetails()
        {
            System.out.println("Printing Booking Details: -\n");
            printHeading();

            for (Booking booking : bookingsList)
            {
                System.out.printf("%n%-15s%-15s%-15s%-20s%-40s%-45s%.2f%n", booking.getBookingId(),booking.getPassengerId(),
                        booking.getVehicleId(),booking.getBookingDate(),booking.getBookingStartPosition(),
                        booking.getBookingEndPosition(),booking.getBookingCost());
            }
        }

        public void currentPassengerBookings()
        {
            System.out.println("Printing Current Passenger bookings: -\n");
            boolean futureBookings = false;
            boolean heading = true;

            for (Booking booking : bookingsList)
            {
                if(booking.getBookingDate().isAfter(LocalDate.now()))
                {
                    if(heading)
                    {
                        printHeading();
                        heading=false;
                    }
                    System.out.printf("%n%-15s%-15s%-15s%-20s%-40s%-45s%.2f%n", booking.getBookingId(),booking.getPassengerId(),
                            booking.getVehicleId(),booking.getBookingDate(),booking.getBookingStartPosition(),
                            booking.getBookingEndPosition(),booking.getBookingCost());
                    futureBookings = true;
                }
                if(!futureBookings)
                {
                    System.out.println("No future bookings in system.");
                    return;
                }
            }
        }

        // ISSUE -> ASSIGNING WRONG VEHICLE ID TO BOOKING
        public void passengerBookingsDateTimeOrder(int passengerID)
        {

            System.out.println("Printing Passenger Bookings in DATE/TIME Order: -\n");
            boolean heading = true;
            boolean passengerHasBookings = false;

            ArrayList<Booking> sortedByDateTime = new ArrayList<>();

            for (Booking booking : bookingsList)
            {
                if(booking.getPassengerId() == passengerID)
                {
                    if(heading)
                    {
                        printHeading();
                        heading=false;
                    }
                    sortedByDateTime.add(booking);
                    passengerHasBookings=true;
                }
            }
            if(!passengerHasBookings)
            {
                System.out.println("No bookings for passenger in system.");
                return;
            }
            sortBookings(sortedByDateTime);
        }

        private void sortBookings(ArrayList<Booking> sortedBookings)
        {
            sortedBookings.sort(new CompareByDateTime());
            for(Booking booking : sortedBookings)
            {
                displayBooking(booking);
            }
        }

        private void displayBooking(Booking booking)
        {
            System.out.printf("%n%-15s%-15s%-15s%-20s%-40s%-45s%.2f%n", booking.getBookingId(),booking.getPassengerId(),
                    booking.getVehicleId(),booking.getBookingDate(),booking.getBookingStartPosition(),
                    booking.getBookingEndPosition(),booking.getBookingCost());
        }

        // SEARCH BOOKINGS FOR VEHICLE ID TO SEE IF IT IS ALREADY BOOKED
        public Booking searchBookingByVehicleID(int vehicleID)
        {
            int position = findBookingByVehicleID(vehicleID);
            if(position >= 0)
            {
                return this.bookingsList.get(position);
            }
            return null;
        }

        public int searchBookingByVehicleID(Booking booking)
        {
            if(findBookingByVehicleID(booking) >= 0)
            {
                return booking.getVehicleId();
            }
            return -1;
        }

        private int findBookingByVehicleID(Booking booking)
        {
            return this.bookingsList.indexOf(booking);
        }

        private int findBookingByVehicleID(int vehicleID)
        {
            for (int i=0; i<this.bookingsList.size(); i++)
            {
                Booking booking = this.bookingsList.get(i);
                if(booking.getVehicleId() == vehicleID)
                {
                    return i;
                }
            }
            return -1;
        }

        public boolean isBooked(int vehicleID)
        {
            boolean booked = false;
            for (Booking booking : bookingsList)
            {
                if (booking.getVehicleId() == vehicleID)
                {
                    booked = true;
                    break;
                }
            }
            return booked;
        }

        public double averageJourneyLength() {

            double sumOfCost=0;
            double distance=0.0;
            int count = 0;
            for (Booking booking : bookingsList)
            {
                count++;
                sumOfCost += booking.getBookingCost();
                distance += calculateDistance2(booking.getBookingStartPosition(), booking.getBookingEndPosition());
            }
            return (sumOfCost*distance)/count;
        }

}

