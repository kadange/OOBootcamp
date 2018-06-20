package org.parkinglot;

import java.util.*;

public class ParkingBoy {
    private List<ParkingLot> managedParkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.managedParkingLots = Arrays.asList(parkingLots);
    }

    public List<Ticket> park(List<Car> cars) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        for (Car car : cars) {
            ParkingLot parkingLot = getAvailableParkingLot();
            tickets.add(parkingLot.park(car));
        }
        return tickets;
    }

    private ParkingLot getAvailableParkingLot() throws NoParkingSpaceException {
//        return managedParkingLots.stream()
//                .filter(managedParkingLot -> managedParkingLot.isParkingLotFull())
//                .findAny()
//                .get();
//        List<ParkingLot> parkingLot = getParkingLotListWithMostAvailableSlot();
        for (ParkingLot managedParkingLot : managedParkingLots) {
            if (!managedParkingLot.isParkingLotFull()) {
                return managedParkingLot;
            }
        }

        throw new NoParkingSpaceException("No Slots available on all parking lot");
    }

    private List<ParkingLot> getParkingLotListWithMostAvailableSlot() {
        Map<Integer, String> map = new LinkedHashMap<>();

        for (ParkingLot managedParkingLot : managedParkingLots) {
            int availableSlot = managedParkingLot.getAvailableSlots();
            map.put(availableSlot, managedParkingLot.parkingName);
        }

        return null;
    }

    public List<Car> getCars(List<Ticket> parkingTickets) throws ParkingLotNotExistingException, NoCarParkedException {
        List<Car> parkedCars = new ArrayList<>();
        for (Ticket parkingTicket : parkingTickets) {
            ParkingLot parkingLot = getParkingLot(parkingTicket);
            parkedCars.add(parkingLot.getCar(parkingTicket));
        }
        return parkedCars;
    }

    private ParkingLot getParkingLot(Ticket parkingTicket) throws ParkingLotNotExistingException {
        for (ParkingLot managedParkingLot : managedParkingLots) {
            if(managedParkingLot.parkingName == parkingTicket.parkingName){
                return managedParkingLot;
            }
        }

        throw new ParkingLotNotExistingException(parkingTicket.parkingName + " is not existing!");
    }
}
