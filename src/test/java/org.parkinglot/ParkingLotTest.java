package org.parkinglot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {
    Car car;
    ParkingLot parkingLot;
    Ticket ticket;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot(5);
        car = new Car();
    }

    @After
    public void tearDown() {
        parkingLot = null;
        car = null;
        ticket = null;
    }

    @Test
    public void driver_can_park_car_then_should_get_ticket() throws Exception {
        ticket = parkingLot.park(car);
        assertNotNull(ticket);
    }

    @Test
    public void driver_give_ticket_then_should_get_car() throws Exception {
        ticket = parkingLot.park(car);
        assertNotNull(parkingLot.getCar(ticket));
    }

    @Test(expected = Exception.class)
    public void driver_can_part_car_but_no_space_then_should_error_NoParkingSpaceException() throws Exception {
        parkingLot = new ParkingLot(0);
        assertNotNull(parkingLot.park(car));
    }

    @Test(expected = Exception.class)
    public void driver_give_ticket_and_no_parked_car_then_should_error_NoCarParkedException() throws Throwable{
        Car carA = new Car();
        Car carB = new Car();

        Ticket ticketA = parkingLot.park(carA);
        System.out.println("CarA is on slot: " + ticketA.slot);
        Ticket ticketB = parkingLot.park(carB);
        System.out.println("CarB is on slot: " + ticketB.slot);
        assertEquals(carA, parkingLot.getCar(ticketA));
        assertEquals(carB, parkingLot.getCar(ticketB));
        assertNotNull(parkingLot.getCar(ticket));
    }
}
