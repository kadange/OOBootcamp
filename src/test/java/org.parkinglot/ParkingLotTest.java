package org.parkinglot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {

    @Test
    public void parking_boy_can_park_car_then_should_return_ticket() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        List<Ticket> parkingTickets = parkingBoy.park(cars);

        assertEquals(1, parkingTickets.size());
    }

    @Test
    public void driver_give_ticket_then_parking_boy_return_parked_car() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        List<Ticket> parkingTickets = parkingBoy.park(cars);
        List<Car> parkedCars = parkingBoy.getCars(parkingTickets);
        assertEquals(cars.get(0), parkedCars.get(0));
    }

    @Test(expected = NoParkingSpaceException.class)
    public void driver_can_park_car_but_no_space_then_should_error_no_parking_space_exception() throws Exception {

        ParkingLot parkingLotA = new ParkingLot("parkingLotA",0);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        assertNotNull(parkingBoy.park(cars));
    }

    @Test(expected = ParkingLotNotExistingException.class)
    public void driver_give_ticket_and_no_parked_car_then_should_error_parking_lot_not_existing_exception() throws Exception{

        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);

        List<Ticket> newTickets = new ArrayList<>();
        newTickets.add(new Ticket("parkingLotB", "Slot3"));
        assertNotNull(parkingBoy.getCars(newTickets));
    }

    @Test(expected = NoCarParkedException.class)
    public void driver_give_ticket_and_no_parked_car_then_should_error_no_car_parked_exception() throws Exception{
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = new ArrayList<>();
        Car carA = new Car();
        cars.add(carA);

        List<Ticket> newTickets = new ArrayList<>();
        newTickets.add(new Ticket("parkingLotA", "Slot3"));
        assertNotNull(parkingBoy.getCars(newTickets));
    }



    @Test
    public void parking_boy_park_multiple_cars_then_should_get_multiple_tickets() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = new ArrayList<>();
        Car carA = new Car();
        Car carB = new Car();
        cars.add(carA);
        cars.add(carB);

        List<Ticket> tickets = parkingBoy.park(cars);
        assertEquals(cars.size(), tickets.size());
    }

    @Test
    public void driver_gives_multiple_tickets_then_parking_boy_return_multiple_cars() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = new ArrayList<>();
        Car carA = new Car();
        Car carB = new Car();
        cars.add(carA);
        cars.add(carB);

        List<Ticket> tickets = parkingBoy.park(cars);
        assertEquals(cars.size(), parkingBoy.getCars(tickets).size());
    }
    
}
