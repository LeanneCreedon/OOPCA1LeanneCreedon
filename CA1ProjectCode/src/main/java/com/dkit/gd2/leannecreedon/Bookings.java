package com.dkit.gd2.leannecreedon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Bookings
{
        // Bookings manager class to handle loading, writing along with calculations and menu methods.

        private ArrayList<Booking> bookingsList = new ArrayList<Booking>();

        public Bookings(String nameOfFile) {
            this.bookingsList = new ArrayList<>();
            loadBookingFromFile(nameOfFile);
        }

        public ArrayList<Booking> getBookingsList() {
            return bookingsList;
        }

        // Loading bookings from bookings.txt file
        private void loadBookingFromFile(String filename)
        {
            try
            {
                Scanner keyboard = new Scanner(new File(filename));
                keyboard.useDelimiter("[,\r\n]+");

                while (keyboard.hasNext()) {

                    int passengerId = keyboard.nextInt();
                    int vehicleId = keyboard.nextInt();
                    int bookingId = keyboard.nextInt();
                    int year = keyboard.nextInt();
                    int month = keyboard.nextInt();
                    int day = keyboard.nextInt();
                    double startLat = keyboard.nextDouble();
                    double startLong = keyboard.nextDouble();
                    double endLat = keyboard.nextDouble();
                    double endLong = keyboard.nextDouble();
                    double bookingCost = keyboard.nextDouble();

                    LocalDate date = LocalDate.of(year, month,day);
                    PositionTracker startPos = new PositionTracker(startLat, startLong);
                    PositionTracker endPos = new PositionTracker(endLat, endLong);

                    bookingsList.add(new Booking(passengerId, vehicleId, bookingId, date, startPos, endPos, bookingCost));
                }
                keyboard.close();

            }
            catch (IOException e)
            {
                System.out.println("Exception thrown. " + e);
            }
        }


        // Writing user input bookings to bookings.txt file
        public void writeBookings()
        {
            try (BufferedWriter bookingFile = new BufferedWriter(new FileWriter("bookings.txt")))
            {
                for (Booking booking : bookingsList) {
                    bookingFile.write(booking.getPassengerId() + "," + booking.getVehicleId() + "," +
                            booking.getBookingId() + "," + booking.getBookingDate().getYear() + "," + booking.getBookingDate().getMonthValue()
                            + "," + booking.getBookingDate().getDayOfMonth() + "," + booking.getBookingStartPosition().getLatitude()
                            + "," + booking.getBookingStartPosition().getLongitude() + "," + booking.getBookingEndPosition().getLatitude()
                            + "," + booking.getBookingEndPosition().getLongitude()
                            + "," + booking.getBookingCost() + "\n");
                }
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }


        /* Booking menu - checks and calculation methods */

        public void addNewBooking(Booking newBooking)
        {
            if(findBooking(newBooking.getVehicleId()) >= 0)
            {
                return;
            }
            bookingsList.add(newBooking);
            writeBookings();
        }

        public boolean updateBooking(Booking oldBooking, Booking newBooking)
        {
            int foundPosition = findBooking(oldBooking);
            if(foundPosition < 0)
            {
                System.out.println(Colours.RED + oldBooking.getBookingId() + ", was not found" + Colours.RESET);
                return false;
            }
            this.bookingsList.set(foundPosition, newBooking);
            writeBookings();
            return true;
        }

        public void removeBooking(Booking booking)
        {
            int foundPosition = findBooking(booking);
            if(foundPosition < 0)
            {
                System.out.println(Colours.RED + booking.getBookingId() + ", was not found" + Colours.RESET);
                return;
            }
            this.bookingsList.remove(foundPosition);
            System.out.println(Colours.GREEN + booking.getBookingId() + ", was deleted" + Colours.RESET);
            writeBookings();
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
            if(!(bookingsList.isEmpty()))
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
            else
            {
                System.out.println(Colours.RED + "No Bookings on the system." + Colours.RESET);
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
            }
            if(!futureBookings)
            {
                System.out.println(Colours.RED + "No future bookings in system." + Colours.RESET);
            }
        }

        public void passengerBookingsDateTimeOrder(int passengerID)
        {
            System.out.println("Printing Passenger Bookings in DATE/TIME Order: -\n");
            boolean heading = true;

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
                }
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

        // SEARCH BOOKINGS FOR VEHICLE ID
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


        // SEARCH BOOKINGS FOR PASSENGER ID TO SEE THEY HAVE A BOOKING
        public Booking searchBookingByPassengerID(int passengerID)
        {
            int position = findBookingByPassengerID(passengerID);
            if(position >= 0)
            {
                return this.bookingsList.get(position);
            }
            return null;
        }

        public int searchBookingByPassengerID(Booking booking)
        {
            if(findBookingByPassengerID(booking) >= 0)
            {
                return booking.getPassengerId();
            }
            return -1;
        }

        private int findBookingByPassengerID(Booking booking)
        {
            return this.bookingsList.indexOf(booking);
        }

        private int findBookingByPassengerID(int passengerID)
        {
            for (int i=0; i<this.bookingsList.size(); i++)
            {
                Booking booking = this.bookingsList.get(i);
                if(booking.getPassengerId() == passengerID)
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

