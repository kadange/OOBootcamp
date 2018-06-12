package org.parkinglot;

import java.util.*;

public class ParkingLot {

    private int parkingLimit;
    private Map<Ticket, Car> slot = new LinkedHashMap<>();
    private Car[] parking2;

    public ParkingLot(int parkingLimit) {
        this.parkingLimit = parkingLimit;
        initParkingLot();
    }

    private void initParkingLot() {
        for (int i = 1; i <= parkingLimit; i++) {
            Ticket ticket = new Ticket("Slot"+i);
            slot.put(ticket, null);
        }
    }

    public List<Ticket> park(List<Car> cars) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        if(!isParkValid()){
            throw new NoParkingSpaceException("No Parking Space Available!");
        }

        List<Ticket> availableTickets = getAvailableSlot();
        if(cars.size() > availableTickets.size()){
            throw new NotEnoughParkingSpaceException("Not Enough Parking Space!");
        }

        for(int i=0; i<cars.size(); i++){
            slot.putIfAbsent(availableTickets.get(i), cars.get(i));
            tickets.add(availableTickets.get(i));
        }
        return tickets;
    }

    private List<Ticket> getAvailableSlot() {
        List<Ticket> ticketList = new ArrayList<>();
        for(Ticket ticket : slot.keySet()){
            if(slot.get(ticket) == null){
                ticketList.add(ticket);
            }
        }
        return ticketList;
    }

    private boolean isParkValid(){
        boolean isValid = false;

        for(Ticket ticket : slot.keySet()){
            if(slot.get(ticket) == null){
                isValid = true;
                break;
            }
        }

        return isValid;
    }

    public Car getCar(Ticket ticket) throws Exception{
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
