package org.parkinglot;

import java.util.*;

public class ParkingLot {

    public String parkingName;
    private int parkingLimit;
    private Map<Ticket, Car> slot = new LinkedHashMap<>();
    private Car[] parking2;

    public ParkingLot(String parkingName, int parkingLimit) {
        this.parkingName = parkingName;
        this.parkingLimit = parkingLimit;
        initParkingLot();
    }

    private void initParkingLot() {
        for (int i = 1; i <= parkingLimit; i++) {
            Ticket ticket = new Ticket(parkingName, "Slot"+i);
            slot.put(ticket, null);
        }
    }

    public Ticket park(Car cars) throws Exception {
        if(isParkingLotFull()){
            throw new NoParkingSpaceException("No Parking Space Available!");
        }

        Ticket availableTicket = getTicketOfFirstAvailableSlot();
        slot.putIfAbsent(availableTicket, cars);

        return availableTicket;
    }

//    public List<Ticket> park(List<Car> cars) throws Exception {
//        List<Ticket> tickets = new ArrayList<>();
//        if(isParkingLotFull()){
//            throw new NoParkingSpaceException("No Parking Space Available!");
//        }
//
//        List<Ticket> availableTickets = getTicketOfFirstAvailableSlot();
//        if(cars.size() > availableTickets.size()){
//            throw new NotEnoughParkingSpaceException("Not Enough Parking Space!");
//        }
//
//        for(int i=0; i<cars.size(); i++){
//            slot.putIfAbsent(availableTickets.get(i), cars.get(i));
//            tickets.add(availableTickets.get(i));
//        }
//        return tickets;
//    }

    private Ticket getTicketOfFirstAvailableSlot() {
        for(Ticket ticket : slot.keySet()){
            if(slot.get(ticket) == null){
                return ticket;
            }
        }
        return null;
    }

    private boolean isParkingLotFull(){
        boolean isFull = true;

        for(Ticket ticket : slot.keySet()){
            if(slot.get(ticket) == null){
                isFull = false;
                break;
            }
        }

        return isFull;
    }

    public Car getCar(Ticket ticket) throws NoCarParkedException{
        isTicketValid(ticket);
        Car car = slot.get(ticket);
        slot.replace(ticket, null);
        return car;
    }

    private void isTicketValid(Ticket ticket) throws NoCarParkedException {
        if(!slot.containsKey(ticket)){
            throw new NoCarParkedException("No car is parked!");
        }
    }
}
