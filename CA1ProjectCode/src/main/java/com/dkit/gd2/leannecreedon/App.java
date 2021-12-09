package com.dkit.gd2.leannecreedon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 *  CA1 - Leanne Creedon
 */
public class App
{
    private static Scanner keyboard = new Scanner(System.in);
    private static Passengers passengersList = new Passengers();
    private static Vehicles vehicles = new Vehicles("vehicles.txt");
    private static Bookings bookings = new Bookings();

    public static void main(String[] args)
    {
        //bookings.printHeading();
        //bookings.printBookingDetails();
        System.out.println("Welcome to Tesla Co. Booking system");
        mainMenu();
    }

    public static void mainMenu ()
    {
        MenuOptions selectedOption = MenuOptions.PRINT_MENU;
        boolean quit = false;
        printMainMenu();

        while (selectedOption != MenuOptions.QUIT)
        {
            System.out.print("\nEnter choice >>> ");
            selectedOption = MenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];

            switch (selectedOption)
            {
                case PRINT_BOOKINGS_MENU:
                    printBookingsMenu();
                    bookingsMenu();
                    return;
                case PRINT_PASSENGER_MENU:
                    printPassengerMenu();
                    passengerMenu();
                    return;
                case QUIT:
                    System.out.println("Shutting down the system.....");
                    quit = true;
                    break;
            }
        }

    }

    public static void bookingsMenu ()
    {
        BookingMenuOptions selectedOption = BookingMenuOptions.PRINT_MENU;

        while (selectedOption != BookingMenuOptions.BACK_TO_MAIN_MENU)
        {
            System.out.print("\nEnter choice >>> ");
            selectedOption = BookingMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];

            switch (selectedOption)
            {
                case PRINT_MENU:
                    printBookingsMenu();
                    break;
                case ADD_BOOKING:
                    addNewBooking();
                    break;
                case EDIT_BOOKING:
                    editBooking();
                    break;
                case DELETE_BOOKING:
                    deleteBooking();
                    break;
                case PRINT_BOOKING_DETAILS:
                    bookings.printBookingDetails();
                    break;
                case SEARCH_BY_SEAT_NUM:
                    searchByType();
                    break;
                case AVERAGE_LENGTH_BOOKING_JOURNEYS:
                    displayAvgJourneyLength();
                    break;
                case BACK_TO_MAIN_MENU:
                    mainMenu();
                    return;
            }
        }
    }

    private static void passengerMenu() {

        PassengerMenuOptions selectedOption = PassengerMenuOptions.PRINT_MENU;

        while (selectedOption != PassengerMenuOptions.BACK_TO_MAIN_MENU)
        {
            System.out.print("\nEnter choice >>> ");
            selectedOption = PassengerMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];

            switch (selectedOption)
            {
                case PRINT_MENU:
                    printPassengerMenu();
                    break;
                case ADD_PASSENGER:
                    addPassenger();
                    break;
                case EDIT_PASSENGER:
                    editPassenger();
                    break;
                case DELETE_PASSENGER:
                    removePassenger();
                    break;
                case PRINT_PASSENGER_DETAILS:
                    passengersList.printPassengerDetails();
                    break;
                case CURRENT_PASSENGER_BOOKINGS:
                    bookings.currentPassengerBookings();
                    break;
                case PASSENGER_BOOKINGS_DATETIME_ORDER:
                    passengerBookingsDateTimeOrder();
                    break;
                case BACK_TO_MAIN_MENU:
                    mainMenu();
                    return;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1 - booking menu\n"
                            + "2 - passenger menu\n"
                            + "3 - quit");
    }

    private static void printBookingsMenu() {
        System.out.println("\nBooking Menu:");
        System.out.println("0 - print choices\n"
                            + "1 - add new booking\n"
                            + "2 - edit booking\n"
                            + "3 - delete booking\n"
                            + "4 - print all booking details\n"
                            + "5 - search vehicles by type\n"
                            + "6 - average length of booking journeys\n"
                            + "7 - main menu");

    }

    private static void printPassengerMenu() {
        System.out.println("\nPassenger Menu:");
        System.out.println("0 - print choices\n"
                            + "1 - add new passenger\n"
                            + "2 - edit passenger\n"
                            + "3 - delete passenger\n"
                            + "4 - print all passenger details\n"
                            + "5 - current passenger bookings\n"
                            + "6 - passenger bookings in date/time order\n"
                            + "7 - main menu");

    }

    // Passenger Menu Methods

    private static void addPassenger()
    {
        String name = getUserInput("Enter passenger name: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(name);
        if(existingPassengerRecord != null)
        {
            System.out.println("Passenger already in system");
            return;
        }
        IDSystem id = IDSystem.getInstance("idSystem.txt");
        String email = getUserInput("Enter email address: ");
        String telephone = getUserInput("Enter phone number: ");
        PositionTracker homePos = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));

        Passenger newPassenger = Passenger.createNewPassenger(name, id, email, telephone, homePos);
        System.out.println("Got all passenger data. Creating new passenger");
        passengersList.addNewPassenger(newPassenger);
        //Passenger.writeNewPassenger(newPassenger);
    }

    private static void editPassenger()
    {
        String name = getUserInput("Enter existing passenger name: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(name);

        if(existingPassengerRecord == null)
        {
            System.out.println("Passenger not found");
            return;
        }
        String newName = getUserInput("Enter passenger name: ");
        IDSystem newId = IDSystem.getInstance("idSystem.txt");
        String newEmail = getUserInput("Enter email address: ");
        String newTelephone = getUserInput("Enter phone number: ");
        PositionTracker newHomePos = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));
        Passenger newPassenger = Passenger.createNewPassenger(newName, newId, newEmail, newTelephone, newHomePos);

        if(passengersList.updatePassenger(existingPassengerRecord, newPassenger))
        {
            System.out.println("Successfully updated");
        }
        else
        {
            System.out.println("Could not update passenger");
        }

    }

    private static void removePassenger() {

        String name = getUserInput("Enter passenger name to remove: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(name);
        if(existingPassengerRecord == null)
        {
            System.out.println("Passenger not found");
            return;
        }
        passengersList.removePassenger(existingPassengerRecord);
    }

    private static void passengerBookingsDateTimeOrder()
    {
        String name = getUserInput("Please enter Passenger name: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(name);
        if(existingPassengerRecord == null)
        {
            System.out.println("Passenger is not on system");
            return;
        }

        bookings.passengerBookingsDateTimeOrder(existingPassengerRecord.getID());
    }

    // Booking Menu Methods

    private static void addNewBooking()
    {
        // SEARCH BY TYPE
        String vehicleType = getUserInput("Please enter vehicle type: ");
        Vehicle existingVehicleRecord = vehicles.searchVehicle(vehicleType);
        if(existingVehicleRecord == null)
        {
            System.out.println("Vehicle not found");
            return;
        }
        // SEARCH BY ID
        int vehicleID = getUserInputInteger("Please enter vehicle ID: ");
        if(vehicles.searchVehicleByID(vehicleID) == null)
        {
            System.out.println("Vehicle not found");
            return;
        }
        // CHECK IF ID MATCHES TYPE
        if(!(vehicles.searchVehicleByID(vehicleID).getType()).equals(existingVehicleRecord.getType()))
        {
            System.out.println("Could not find a vehicle type with chosen vehicle ID");
            return;
        }
        // CHECK IF VEHICLE IS ALREADY BOOKED
        boolean existingBookingRecord = bookings.isBooked(vehicleID);
        if(existingBookingRecord)
        {
            System.out.println("Vehicle, " + vehicleID + " is already booked");
            return;
        }
        // SEARCH FOR PASSENGER
        String passengerName = getUserInput("Please enter passenger name: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(passengerName);
        if(existingPassengerRecord == null)
        {
            System.out.println("Passenger not found");
            return;
        }
        // CREATE BOOKING
        int chosenVehicleID = existingVehicleRecord.getId();
        int passengerID = existingPassengerRecord.getID();

        IDSystem bookingId = IDSystem.getInstance("idSystem.txt");
        LocalDate date = LocalDate.of((getUserInputInteger("Enter Year: ")),
                (getUserInputInteger("Enter Month: ")),(getUserInputInteger("Enter Day: ")));
        System.out.println("Booking start point:");
        PositionTracker bookingPosStart = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));
        System.out.println("Booking end point:");
        PositionTracker bookingPosEnd = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));
        PositionTracker vehicleDepotPos = vehicles.searchVehicleDepot(existingVehicleRecord);
        double price = bookings.calculatePrice(bookingPosStart, bookingPosEnd, vehicleDepotPos);
        Booking newBooking = Booking.createNewBooking(passengerID,chosenVehicleID, bookingId, date, bookingPosStart, bookingPosEnd, price);
        System.out.println("Got all booking data. Creating new booking");
        bookings.addNewBooking(newBooking);
    }



    private static void editBooking()
    {
        int id = getUserInputInteger("Enter existing booking ID: ");
        Booking existingBookingRecord = bookings.searchBooking(id);

        if(existingBookingRecord == null)
        {
            System.out.println("Booking not found");
            return;
        }
        String vehicleType = getUserInput("Please enter new vehicle type: ");
        Vehicle newExistingVehicleRecord = vehicles.searchVehicle(vehicleType);
        if(newExistingVehicleRecord == null)
        {
            System.out.println("Vehicle not found");
            return;
        }
        LocalDate newDate = LocalDate.of((getUserInputInteger("Enter Year: ")),
                (getUserInputInteger("Enter Month: ")),(getUserInputInteger("Enter Day: ")));
        System.out.println("Booking start point:");
        PositionTracker newBookingPosStart = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));
        System.out.println("Booking end point:");
        PositionTracker newBookingPosEnd = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));
        PositionTracker newVehicleDepotPos = vehicles.searchVehicleDepot(newExistingVehicleRecord);
        double newPrice = bookings.calculatePrice(newBookingPosStart, newBookingPosEnd, newVehicleDepotPos);

        System.out.println("Processing...");
        Booking updatingBooking = Booking.updateBooking(id, newDate, newBookingPosStart, newBookingPosEnd, newPrice);

        if(bookings.updateBooking(existingBookingRecord, updatingBooking))
        {
            System.out.println("Successfully updated");
        }
        else
        {
            System.out.println("Could not update booking");
        }
    }

    private static void deleteBooking()
    {
        int id = getUserInputInteger("Enter bookingID to remove: ");
        Booking existingBookingRecord = bookings.searchBooking(id);
        if(existingBookingRecord == null)
        {
            System.out.println("Booking not found");
            return;
        }
        bookings.removeBooking(existingBookingRecord);
    }

    private static void searchByType()
    {
        String vehicleType = getUserInput("Please enter vehicle type: ");
        Vehicle existingVehicleRecord = vehicles.searchVehicle(vehicleType);
        if(existingVehicleRecord == null)
        {
            System.out.println("Vehicles of type not found.");
            return;
        }
        vehicles.printVehiclesOfType(vehicleType);
    }

    private static void displayAvgJourneyLength()
    {
        System.out.println("Printing average bookings Journey length: \n");
        System.out.printf("%s%.2f%s%n","=> ",bookings.averageJourneyLength(), " miles.");
    }


    // Get user input

    private static String getUserInput(String message)
    {
        System.out.println(message);
        return keyboard.nextLine();
    }

    public static double getUserInputDouble(String message)
    {
        System.out.println(message);
        return Double.parseDouble(keyboard.nextLine());
    }

    private static int getUserInputInteger(String message)
    {
        System.out.println(message);
        return Integer.parseInt(keyboard.nextLine());
    }

}
