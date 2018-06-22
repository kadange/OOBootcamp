package org.parkinglot;

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
        List<Car> cars = createCars(1);
        List<Ticket> parkingTickets = parkingBoy.park(cars);

        assertEquals(1, parkingTickets.size());
    }

    @Test
    public void driver_give_ticket_then_parking_boy_return_parked_car() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = createCars(1);
        List<Ticket> parkingTickets = parkingBoy.park(cars);
        List<Car> parkedCars = parkingBoy.getCars(parkingTickets);
        assertEquals(cars.get(0), parkedCars.get(0));
    }

    @Test(expected = NoParkingSpaceException.class)
    public void driver_can_park_car_but_no_space_then_should_error_no_parking_space_exception() throws Exception {

        ParkingLot parkingLotA = new ParkingLot("parkingLotA",0);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = createCars(1);
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
        List<Car> cars = createCars(1);

        List<Ticket> newTickets = new ArrayList<>();
        newTickets.add(new Ticket("parkingLotA", "Slot3"));
        assertNotNull(parkingBoy.getCars(newTickets));
    }

    @Test
    public void parking_boy_park_multiple_cars_then_should_get_multiple_tickets() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = createCars(2);

        List<Ticket> tickets = parkingBoy.park(cars);
        assertEquals(cars.size(), tickets.size());
    }

    @Test
    public void driver_gives_multiple_tickets_then_parking_boy_return_multiple_cars() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA);
        List<Car> cars = createCars(2);

        List<Ticket> tickets = parkingBoy.park(cars);
        assertEquals(cars.size(), parkingBoy.getCars(tickets).size());
    }

    @Test
    public void parking_boy_parks_multiple_cars_but_cannot_fit_on_the_parking_lot_available_then_should_park_on_the_next_parking_lot() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 1);
        ParkingLot parkingLotB = new ParkingLot("parkingLotB", 1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA, parkingLotB);
        List<Car> cars = createCars(2);

        List<Ticket> tickets = parkingBoy.park(cars);
        assertEquals(cars.size(), tickets.size());
        assertEquals("parkingLotA", tickets.get(0).parkingName);
        assertEquals("parkingLotB", tickets.get(1).parkingName);
    }

    @Test
    public void parking_boy_parks_multiple_cars_but_cannot_fit_on_the_parking_lot_available_then_should_park_on_the_next_parking_lot2() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 2);
        ParkingLot parkingLotB = new ParkingLot("parkingLotB", 2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA, parkingLotB);
        List<Car> cars = createCars(3);

        List<Ticket> tickets = parkingBoy.park(cars);
        assertEquals(cars.size(), tickets.size());
        assertEquals("parkingLotA", tickets.get(0).parkingName);
        assertEquals("parkingLotA", tickets.get(1).parkingName);
        assertEquals("parkingLotB", tickets.get(2).parkingName);
    }

    @Test
    public void parking_boy_parks_multiple_cars_but_cannot_fit_on_the_parking_lot_available_then_should_park_on_the_next_parking_lot3() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 1);
        ParkingLot parkingLotB = new ParkingLot("parkingLotB", 2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotA, parkingLotB);
        List<Car> cars = createCars(3);

        List<Ticket> tickets = parkingBoy.park(cars);
        assertEquals(cars.size(), tickets.size());
        assertEquals("parkingLotA", tickets.get(0).parkingName);
        assertEquals("parkingLotB", tickets.get(1).parkingName);
        assertEquals("parkingLotB", tickets.get(2).parkingName);
    }

    @Test
    public void smart_parking_boy_should_park_car_on_the_parking_lot_that_has_most_available_slots() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 5);
        ParkingLot parkingLotB = new ParkingLot("parkingLotB", 5);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotA, parkingLotB);
        List<Car> carListA = createCars(2);

        List<Ticket> ticketListA = smartParkingBoy.park(carListA);
        assertEquals("parkingLotA", ticketListA.get(0).parkingName);
        assertEquals("parkingLotB", ticketListA.get(1).parkingName);

        List<Car> carListB = createCars(2);

        List<Ticket> ticketListB = smartParkingBoy.park(carListB);
        assertEquals("parkingLotA", ticketListB.get(0).parkingName);
        assertEquals("parkingLotB", ticketListB.get(1).parkingName);
    }

    @Test
    public void super_parking_boy_should_park_car_on_the_parking_lot_that_has_most_available_space_rate() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 10);
        ParkingLot parkingLotB = new ParkingLot("parkingLotB", 5);
        List<Car> carsA = createCars(7);
        List<Car> carsB = createCars(2);

        for (Car car : carsA) {
            parkingLotA.park(car);
        }

        for (Car car : carsB) {
            parkingLotB.park(car);
        }
        assertEquals(3,parkingLotA.getAvailableSlots(), 0.0);
        assertEquals(3,parkingLotB.getAvailableSlots(), 0.0);

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotA, parkingLotB);
        List<Car> carsC = createCars(1);
        List<Ticket> ticketsA = superParkingBoy.park(carsC);
        assertEquals(3,parkingLotA.getAvailableSlots(), 0.0);
        assertEquals("parkingLotB",ticketsA.get(0).parkingName);
    }

    @Test
    public void super_parking_boy_should_park_multiple_cars_on_the_parking_lot_that_has_most_available_space_rate() throws Exception {
        ParkingLot parkingLotA = new ParkingLot("parkingLotA", 10);
        ParkingLot parkingLotB = new ParkingLot("parkingLotB", 5);
        List<Car> carsA = createCars(7);
        List<Car> carsB = createCars(2);

        for (Car car : carsA) {
            parkingLotA.park(car);
        }

        for (Car car : carsB) {
            parkingLotB.park(car);
        }
        assertEquals(3,parkingLotA.getAvailableSlots(), 0.0);
        assertEquals(3,parkingLotB.getAvailableSlots(), 0.0);

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotA, parkingLotB);
        List<Car> carsC = createCars(3);
        List<Ticket> tickets = superParkingBoy.park(carsC);
        assertEquals("parkingLotB",tickets.get(0).parkingName);
        assertEquals("parkingLotB",tickets.get(1).parkingName);
        assertEquals("parkingLotA",tickets.get(2).parkingName);
    }

    private List<Car> createCars(int numberOfCars) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfCars; i++) {
            cars.add(new Car());
        }
        return cars;
    }
}
