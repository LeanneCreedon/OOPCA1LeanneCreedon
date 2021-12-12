package com.dkit.gd2.leannecreedon;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Unit tests for the Application
 */
public class AppTest 
{

    // Test construction of Truck object.
    @Test
    public void createTruck()
    {
        Truck truck= new Truck("Truck","Nissan","Urvan",4,"181MN6538107",
                6.00,2021, 5,24,126000,53.2543,-6.4444,240);
        assertEquals("Nissan", truck.getMake());
        assertEquals("Urvan", truck.getModel());
        assertEquals("181MN6538107", truck.getRegistrationNumber());
        assertEquals(6.00, truck.getCostPerMile(),0.005);
        assertEquals(2021, truck.getLastServicedDate().getYear());
        assertEquals(5, truck.getLastServicedDate().getMonthValue());
        assertEquals(24, truck.getLastServicedDate().getDayOfMonth());
        assertEquals(126000,truck.getMileage());
        assertEquals(53.2543, truck.getVehicleDepotPosition().getLatitude(),0.00005);
        assertEquals(-6.4444, truck.getVehicleDepotPosition().getLongitude(),0.00005);
        assertEquals(240,truck.getLoadSpace(),0.05);
    }

    // Test construction of Van object.
    @Test
    public void createVan()
    {
        Van van = new Van("Van","Fiat","Runround",6,"151D987105",
                6.00,2020,12,15,100034,53.3456,-6.3421,140);
        assertEquals("Fiat", van.getMake());
        assertEquals("Runround", van.getModel());
        assertEquals("151D987105", van.getRegistrationNumber());
        assertEquals(6.00, van.getCostPerMile(),0.005);
        assertEquals(2020, van.getLastServicedDate().getYear());
        assertEquals(12, van.getLastServicedDate().getMonthValue());
        assertEquals(15, van.getLastServicedDate().getDayOfMonth());
        assertEquals(100034,van.getMileage());
        assertEquals(53.3456, van.getVehicleDepotPosition().getLatitude(),0.00005);
        assertEquals(-6.3421, van.getVehicleDepotPosition().getLongitude(),0.00005);
        assertEquals(140,van.getLoadSpace(),0.05);
    }

    // Test construction of FourByFour object.
    @Test
    public void createFourByFour()
    {
        FourByFour fourByFour = new FourByFour("FourByFour","Skoda","Fabia",3,"112GL3478142",
                8.00,2013, 6,23,123030,54.2243,-5.4344,145);
        assertEquals("Skoda", fourByFour.getMake());
        assertEquals("Fabia", fourByFour.getModel());
        assertEquals("112GL3478142", fourByFour.getRegistrationNumber());
        assertEquals(8.00, fourByFour.getCostPerMile(),0.005);
        assertEquals(2013, fourByFour.getLastServicedDate().getYear());
        assertEquals(6, fourByFour.getLastServicedDate().getMonthValue());
        assertEquals(23, fourByFour.getLastServicedDate().getDayOfMonth());
        assertEquals(123030,fourByFour.getMileage());
        assertEquals(54.2243, fourByFour.getVehicleDepotPosition().getLatitude(),0.00005);
        assertEquals(-5.4344, fourByFour.getVehicleDepotPosition().getLongitude(),0.00005);
        assertEquals(145,fourByFour.getNumberOfSeats());
    }

    // Test construction of Car object.
    @Test
    public void createCar()
    {
        Car car = new Car("Car","Opel","Corsa",7,"191SL53601",
                10.00,2012, 9,12,84000,61.4786,-7.2943,134);
        assertEquals("Opel", car.getMake());
        assertEquals("Corsa", car.getModel());
        assertEquals("191SL53601", car.getRegistrationNumber());
        assertEquals(10.00, car.getCostPerMile(),0.005);
        assertEquals(2012, car.getLastServicedDate().getYear());
        assertEquals(9, car.getLastServicedDate().getMonthValue());
        assertEquals(12, car.getLastServicedDate().getDayOfMonth());
        assertEquals(84000,car.getMileage());
        assertEquals(61.4786, car.getVehicleDepotPosition().getLatitude(),0.00005);
        assertEquals(-7.2943, car.getVehicleDepotPosition().getLongitude(),0.00005);
        assertEquals(134,car.getNumberOfSeats());
    }

    // Test construction of Passenger object.
    @Test
    public void createPassenger()
    {
        Passenger passenger = new Passenger("Leanne Creedon",438,"notmyrealaddress@gmail.com","0927684459",
                new PositionTracker(34.5,-76.4));
        assertEquals("Leanne Creedon", passenger.getName());
        assertEquals(438, passenger.getID());
        assertEquals("notmyrealaddress@gmail.com", passenger.getEmail());
        assertEquals("0927684459", passenger.getTelephone());
        assertEquals(34.5, passenger.getHomePosition().getLatitude(),0.00005);
        assertEquals(-76.4, passenger.getHomePosition().getLongitude(),0.00005);
    }

    // Test construction of Booking object.
    @Test
    public void createBooking()
    {
        Booking booking = new Booking(438,109, 448, LocalDate.of(2022, 1, 12),
                new PositionTracker(56.4,-87.5),
                new PositionTracker(34.2,-76.4), 178.44112638647698);
        assertEquals(438, booking.getPassengerId());
        assertEquals(109, booking.getVehicleId());
        assertEquals(448, booking.getBookingId());
        assertEquals(2022, booking.getBookingDate().getYear());
        assertEquals(1, booking.getBookingDate().getMonthValue());
        assertEquals(12,booking.getBookingDate().getDayOfMonth());
        assertEquals(56.4, booking.getBookingStartPosition().getLatitude(),0.00005);
        assertEquals(-87.5, booking.getBookingStartPosition().getLongitude(), 0.00005);
        assertEquals(34.2,booking.getBookingEndPosition().getLatitude(), 0.00005);
        assertEquals(-76.4,booking.getBookingEndPosition().getLongitude(), 0.00005);
        assertEquals(178.44112638647698,booking.getBookingCost(), 0.00005);
    }


}
