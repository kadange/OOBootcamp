package org.parkinglot;

import java.util.*;

public class ParkingLot {

    private int parkingLimit;
    private Map<Ticket, Car> slot = new HashMap<>();
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

    public Ticket park(Car car) throws Exception{
        if(!isParkValid()){
            throw new NoParkingSpaceException("No Parking Space Available!");
        }

        List<Ticket> ticketList = getAvailableSlot();
        slot.putIfAbsent(ticketList.get(0), car);
        return ticketList.get(0);
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
        if(!slot.containsKey(ticket)){
            throw new NoCarParkedException("No car is parked!");
        }
        Car car = slot.get(ticket);
        slot.replace(ticket, null);
        return car;
    }
}
