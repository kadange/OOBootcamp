package org.parkinglot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {
    Car car;
    ParkingLot parkingLot;
    List<Ticket> ticket;
    List<Car> cars;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot(5);
        cars = new ArrayList<>();
        ticket = new ArrayList<>();
        car = new Car();
        cars.add(car);
    }

    @After
    public void tearDown() {
        parkingLot = null;
        car = null;
        ticket = null;
        cars = null;
    }

    @Test
    public void driver_can_park_car_then_should_get_ticket() throws Exception {
        ticket.addAll(parkingLot.park(cars));
        assertNotNull(ticket);
    }

    @Test
    public void driver_give_ticket_then_should_get_car() throws Exception {
        ticket = parkingLot.park(cars);
        for(Ticket ticket : ticket){
            assertNotNull(parkingLot.getCar(ticket));
        }
    }

    @Test(expected = Exception.class)
    public void driver_can_part_car_but_no_space_then_should_error_NoParkingSpaceException() throws Exception {
        parkingLot = new ParkingLot(0);
        assertNotNull(parkingLot.park(cars));
    }

    @Test(expected = Exception.class)
    public void driver_give_ticket_and_no_parked_car_then_should_error_NoCarParkedException() throws Throwable{
        ParkingLot parkingLot = new ParkingLot(5);
        List<Car> cars = new ArrayList<>();
        Car carA = new Car();
        Car carB = new Car();
        cars.add(carA);
        cars.add(carB);

        List<Ticket> tickets = parkingLot.park(cars);
//        for(int i=0; i<tickets.size(); i++){
//            System.out.println("Car" + i + " is on slot: " + tickets.get(i));
//            assertEquals(cars.get(i), parkingLot.getCar(tickets.get(i)));
//        }
        assertNotNull(parkingLot.getCar(new Ticket("Slot3")));
    }

    @Test(expected = Exception.class)
    public void given_multiple_cars_when_parked_then_should_get_multiple_tickets() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        List<Car> cars = new ArrayList<>();
        Car carA = new Car();
        Car carB = new Car();
        cars.add(carA);
        cars.add(carB);

        List<Ticket> tickets = parkingLot.park(cars);
        for(int i=0; i<tickets.size(); i++){
            System.out.println("Car" + i + " is on slot: " + tickets.get(i).slot);
            assertEquals(cars.get(i), parkingLot.getCar(tickets.get(i)));
        }
    }

    @Test
    public void given_multiple_tickets_when_getting_cars_then_should_get_cars() throws Exception {
        ParkingLot parkingLot = new ParkingLot(5);
        List<Car> cars = new ArrayList<>();
        Car carA = new Car();
        Car carB = new Car();
        cars.add(carA);
        cars.add(carB);

        List<Ticket> tickets = parkingLot.park(cars);
        for(int i=0; i<tickets.size(); i++){
            System.out.println("Car" + i + " is on slot: " + tickets.get(i).slot);
            assertEquals(cars.get(i), parkingLot.getCar(tickets.get(i)));
        }
    }
}
