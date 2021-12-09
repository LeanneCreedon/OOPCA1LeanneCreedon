package com.dkit.gd2.leannecreedon;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
                6.00,2021,05,24,126000,53.2543,-6.4444,240);
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
}
