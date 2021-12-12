package com.dkit.gd2.leannecreedon;


import java.time.LocalDate;
import java.util.Scanner;

/**
 * References:
 *
 * Formatting tables help => https://stackoverflow.com/questions/18672643/how-to-print-a-table-of-information-in-java
 * Nial O'Reilly helped me => with understanding the structure of the system more. eg. Passenger, Passengers, Booking, Bookings connections.
 * Prithvi helped me => with a few issues that came up in my code. eg. in addNewBooking method, when I check if that vehicle id is already
 * booked, it was skipping that check. He helped me fix that issue.
 *
 *
 *
 *  CA1 - Leanne Creedon
 */
public class App
{
    private static final Scanner keyboard = new Scanner(System.in);
    private static final Passengers passengersList = new Passengers("passengers.txt");
    private static final Vehicles vehicles = new Vehicles("vehicles.txt");
    private static final Bookings bookings = new Bookings("bookings.txt");

    public static void main(String[] args)
    {
        System.out.println(Colours.BLUE + "\nWelcome to Tesla Co. Booking system");
        for(int i=0; i<35; i++) {
            System.out.print('-');
        }
        System.out.println(Colours.RESET + "\n");
        mainMenu();
    }

    public static void mainMenu ()
    {
        MenuOptions selectedOption = MenuOptions.PRINT_MENU;
        printMainMenu();

        while (selectedOption != MenuOptions.QUIT)
        {
            try
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
                        System.out.println(Colours.BLUE + "Shutting down the system....." + Colours.RESET);
                        break;
                    default:
                        throw new Exception("Shouting message");
                }
            }
            catch (Exception e)
            {
                System.out.println(Colours.RED + "\nPlease enter valid option" + Colours.RESET);
            }
        }

    }

    public static void bookingsMenu ()
    {
        BookingMenuOptions selectedOption = BookingMenuOptions.PRINT_MENU;

        while (selectedOption != BookingMenuOptions.BACK_TO_MAIN_MENU)
        {
            try
            {
                System.out.print("\nEnter choice (0 = print menu) >>> ");
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
                    default:
                        throw new Exception("Shouting message");
                }
            }
            catch (Exception e)
            {
                System.out.println(Colours.RED + "\nPlease enter valid option" + Colours.RESET);
            }
        }
    }

    private static void passengerMenu() {

        PassengerMenuOptions selectedOption = PassengerMenuOptions.PRINT_MENU;

        while (selectedOption != PassengerMenuOptions.BACK_TO_MAIN_MENU)
        {
            try
            {
                System.out.print("\nEnter choice (0 = print menu) >>> ");
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
                        currentPassengerBookings();
                        break;
                    case PASSENGER_BOOKINGS_DATETIME_ORDER:
                        passengerBookingsDateTimeOrder();
                        break;
                    case BACK_TO_MAIN_MENU:
                        mainMenu();
                        return;
                    default:
                        throw new Exception("Shouting message");
                }
            }
            catch (Exception e)
            {
                System.out.println(Colours.RED + "\nPlease enter valid option" + Colours.RESET);
            }
        }
    }

    private static void printMainMenu() {
        System.out.println(Colours.PURPLE + "\nMain Menu:" + Colours.RESET);
        System.out.println("1 - booking menu\n"
                            + "2 - passenger menu\n"
                            + "3 - quit");
    }

    private static void printBookingsMenu() {
        System.out.println(Colours.PURPLE + "\nBooking Menu:" + Colours.RESET);
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
        System.out.println(Colours.PURPLE + "\nPassenger Menu:" + Colours.RESET);
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
            System.out.println(Colours.RED + "Passenger already in system" + Colours.RESET);
            return;
        }
        IDSystem id = IDSystem.getInstance("idSystem.txt");
        String email = getUserInput("Enter email address: ");
        String telephone = getUserInput("Enter phone number: ");
        PositionTracker homePos = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));

        System.out.println("\nProcessing...");
        Passenger newPassenger = Passenger.createNewPassenger(name, id, email, telephone, homePos);
        System.out.println(Colours.GREEN + "Got all passenger data. Creating new passenger" + Colours.RESET);
        passengersList.addNewPassenger(newPassenger);
    }

    private static void editPassenger()
    {
        String name = getUserInput("Enter existing passenger name: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(name);

        if(existingPassengerRecord == null)
        {
            System.out.println(Colours.RED + "Passenger not found" + Colours.RESET);
            return;
        }
        String newName = getUserInput("Enter passenger name: ");
        int oldId = existingPassengerRecord.getID();
        String newEmail = getUserInput("Enter email address: ");
        String newTelephone = getUserInput("Enter phone number: ");
        PositionTracker newHomePos = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));
        Passenger newPassenger = Passenger.updatePassenger(newName, oldId, newEmail, newTelephone, newHomePos);

        System.out.println("\nProcessing...");
        if(passengersList.updatePassenger(existingPassengerRecord, newPassenger))
        {
            System.out.println(Colours.GREEN + "Successfully updated" + Colours.RESET);
        }
        else
        {
            System.out.println(Colours.RED + "Could not update passenger" + Colours.RESET);
        }
    }

    private static void removePassenger() {

        String name = getUserInput("Enter passenger name to remove: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(name);
        if(existingPassengerRecord == null)
        {
            System.out.println(Colours.RED + "Passenger not found" + Colours.RESET);
            return;
        }
        passengersList.removePassenger(existingPassengerRecord);
    }

    public static void currentPassengerBookings()
    {
        if(bookings.getBookingsList().isEmpty())
        {
            System.out.println(Colours.RED + "No bookings on system." + Colours.RESET);
        }
        bookings.currentPassengerBookings();
    }

    private static void passengerBookingsDateTimeOrder()
    {
        String name = getUserInput("Please enter Passenger name: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(name);
        if(existingPassengerRecord == null)
        {
            System.out.println(Colours.RED + "Passenger is not on system" + Colours.RESET);
            return;
        }
        Booking existingPassengerBooking = bookings.searchBookingByPassengerID(existingPassengerRecord.getID());
        if(existingPassengerBooking == null)
        {
            System.out.println(Colours.RED + "Passenger does not have bookings on system" + Colours.RESET);
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
            System.out.println(Colours.RED + "Vehicle not found" + Colours.RESET);
            return;
        }
        // SEARCH BY ID
        int vehicleID = getUserInputInteger("Please enter vehicle ID: ");
        if(vehicles.searchVehicleByID(vehicleID) == null)
        {
            System.out.println(Colours.RED + "Vehicle not found" + Colours.RESET);
            return;
        }
        // CHECK IF ID MATCHES TYPE
        if(!(vehicles.searchVehicleByID(vehicleID).getType()).equals(existingVehicleRecord.getType()))
        {
            System.out.println(Colours.RED + "Could not find a vehicle type with chosen vehicle ID" + Colours.RESET);
            return;
        }
        // CHECK IF VEHICLE IS ALREADY BOOKED
        boolean existingBookingRecord = bookings.isBooked(vehicleID);
        if(existingBookingRecord)
        {
            System.out.println(Colours.RED + "Vehicle, " + vehicleID + " is already booked" + Colours.RESET);
            return;
        }
        // SEARCH FOR PASSENGER
        String passengerName = getUserInput("Please enter passenger name: ");
        Passenger existingPassengerRecord = passengersList.searchPassenger(passengerName);
        if(existingPassengerRecord == null)
        {
            System.out.println(Colours.RED + "Passenger not found" + Colours.RESET);
            return;
        }
        // CREATE BOOKING
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

        System.out.println("\nProcessing...");
        Booking newBooking = Booking.createNewBooking(passengerID, vehicleID, bookingId, date, bookingPosStart, bookingPosEnd, price);
        System.out.println(Colours.GREEN + "Got all booking data. Creating new booking" + Colours.RESET);
        bookings.addNewBooking(newBooking);
    }


    private static void editBooking()
    {
        int oldBookingId = getUserInputInteger("Enter existing booking ID: ");
        Booking existingBookingRecord = bookings.searchBooking(oldBookingId);

        if(existingBookingRecord == null)
        {
            System.out.println(Colours.RED + "Booking not found" + Colours.RESET);
            return;
        }
        String vehicleType = getUserInput("Please enter new vehicle type: ");
        Vehicle newExistingVehicleRecord = vehicles.searchVehicle(vehicleType);
        if(newExistingVehicleRecord == null)
        {
            System.out.println(Colours.RED + "Vehicle not found" + Colours.RESET);
            return;
        }
        // SEARCH BY ID
        int vehicleID = getUserInputInteger("Please enter vehicle ID: ");
        if(vehicles.searchVehicleByID(vehicleID) == null)
        {
            System.out.println(Colours.RED + "Vehicle not found" + Colours.RESET);
            return;
        }
        // CHECK IF ID MATCHES TYPE
        if(!(vehicles.searchVehicleByID(vehicleID).getType()).equals(newExistingVehicleRecord.getType()))
        {
            System.out.println(Colours.RED + "Could not find a vehicle type with chosen vehicle ID" + Colours.RESET);
            return;
        }
        int passengerID = existingBookingRecord.getPassengerId();
        LocalDate newDate = LocalDate.of((getUserInputInteger("Enter Year: ")),
                (getUserInputInteger("Enter Month: ")),(getUserInputInteger("Enter Day: ")));

        System.out.println("Booking start point:");
        PositionTracker newBookingPosStart = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));
        System.out.println("Booking end point:");
        PositionTracker newBookingPosEnd = new PositionTracker(getUserInputDouble("Enter latitude: "),getUserInputDouble("Enter longitude: "));

        PositionTracker newVehicleDepotPos = vehicles.searchVehicleDepot(newExistingVehicleRecord);
        double newPrice = bookings.calculatePrice(newBookingPosStart, newBookingPosEnd, newVehicleDepotPos);

        System.out.println("\nProcessing...");
        Booking updatingBooking = Booking.updateBooking(passengerID, vehicleID, oldBookingId, newDate, newBookingPosStart, newBookingPosEnd, newPrice);

        if(bookings.updateBooking(existingBookingRecord, updatingBooking))
        {
            System.out.println(Colours.GREEN +"Successfully updated" + Colours.RESET);
        }
        else
        {
            System.out.println(Colours.RED +"Could not update booking" + Colours.RESET);
        }
    }

    private static void deleteBooking()
    {
        int id = getUserInputInteger("Enter bookingID to remove: ");
        Booking existingBookingRecord = bookings.searchBooking(id);
        if(existingBookingRecord == null)
        {
            System.out.println(Colours.RED + "Booking not found" + Colours.RESET);
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
            System.out.println(Colours.RED + "Vehicles of type not found." + Colours.RESET);
            return;
        }
        vehicles.printVehiclesOfType(vehicleType);
    }

    private static void displayAvgJourneyLength()
    {
        if(bookings.getBookingsList().isEmpty())
        {
            System.out.println(Colours.RED + "No bookings on system." + Colours.RESET);
            return;
        }
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
