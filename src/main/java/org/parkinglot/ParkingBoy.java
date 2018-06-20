package org.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> managedParkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.managedParkingLots = Arrays.asList(parkingLots);
    }

    public List<Ticket> park(List<Car> cars) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        for (Car car : cars) {
            ParkingLot parkingLot = getMostAvailableParkingLot();
            tickets.add(parkingLot.park(car));
        }
        return tickets;
    }

    private ParkingLot getMostAvailableParkingLot() {
        return managedParkingLots.get(0);
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
