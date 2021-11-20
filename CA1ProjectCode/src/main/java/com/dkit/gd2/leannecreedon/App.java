package com.dkit.gd2.leannecreedon;

import java.util.Scanner;

/**
 *  CA1 - Leanne Creedon
 */
public class App
{
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args)
    {
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
                case PRINT_MENU:
                    printMainMenu();
                    break;
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
                    printBookingDetails();
                    break;
                case SEARCH_BY_SEAT_NUM:
                    searchBySeatNum();
                    break;
                case AVERAGE_LENGTH_BOOKING_JOURNEYS:
                    averageJourneyLength();
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
                    addNewPassenger();
                    break;
                case EDIT_PASSENGER:
                    editPassenger();
                    break;
                case DELETE_PASSENGER:
                    deletePassenger();
                    break;
                case PRINT_PASSENGER_DETAILS:
                    printPassengerDetails();
                    break;
                case CURRENT_PASSENGER_BOOKINGS:
                    currentPassengerBookings();
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
        System.out.println("0 - print choices\n"
                            + "1 - booking menu\n"
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
                            + "5 - search vehicles by number of seats\n"
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

    // Booking Menu Methods

    private static void addNewBooking()
    {
        System.out.println("ADD NEW BOOKING");
    }

    private static void editBooking()
    {
        System.out.println("EDIT BOOKING");
    }

    private static void deleteBooking()
    {
        System.out.println("DELETE BOOKING");
    }

    private static void printBookingDetails()
    {
        System.out.println("PRINT BOOKING DETAILS");
    }

    private static void searchBySeatNum()
    {
        System.out.println("SEARCH BOOKING BY SEAT NUM");
    }

    private static void averageJourneyLength()
    {
        System.out.println("AVERAGE BOOKING JOURNEY LENGTH");
    }


    // Passenger Menu Methods

    private static void addNewPassenger()
    {
        System.out.println("ADD NEW PASSENGER");
    }

    private static void editPassenger()
    {
        System.out.println("EDIT PASSENGER");
    }

    private static void deletePassenger()
    {
        System.out.println("DELETE PASSENGER");
    }

    private static void printPassengerDetails()
    {
        System.out.println("PRINT PASSENGER DETAILS");
    }

    private static void currentPassengerBookings()
    {
        System.out.println("CURRENT PASSENGER BOOKINGS");
    }

    private static void passengerBookingsDateTimeOrder()
    {
        System.out.println("PASSENGER BOOKINGS IN DATE/TIME ORDER");
    }

    // Get user input

    private static String getUserInput(String message)
    {
        System.out.println(message);
        return keyboard.nextLine();
    }
}
