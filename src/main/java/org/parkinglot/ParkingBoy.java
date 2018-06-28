package org.parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    List<ParkingLot> managedParkingLots;

    ParkingBoy(ParkingLot... parkingLots) {
        this.managedParkingLots = Arrays.asList(parkingLots);
    }

    List<Ticket> park(List<Car> cars) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        for (Car car : cars) {
            ParkingLot parkingLot = getAvailableParkingLot();
            tickets.add(parkingLot.park(car));
        }
        return tickets;
    }

    protected ParkingLot getAvailableParkingLot() throws NoParkingSpaceException {
        for (ParkingLot managedParkingLot : managedParkingLots) {
            if (!managedParkingLot.isParkingLotFull()) {
                return managedParkingLot;
            }
        }

        throw new NoParkingSpaceException("No Slots available on all parking lot");
    }


    List<Car> getCars(List<Ticket> parkingTickets) throws ParkingLotNotExistingException, NoCarParkedException {
        List<Car> parkedCars = new ArrayList<>();
        for (Ticket parkingTicket : parkingTickets) {
            ParkingLot parkingLot = getParkingLot(parkingTicket);
            parkedCars.add(parkingLot.getCar(parkingTicket));
        }
        return parkedCars;
    }

    private ParkingLot getParkingLot(Ticket parkingTicket) throws ParkingLotNotExistingException {
        for (ParkingLot managedParkingLot : managedParkingLots) {
            if(managedParkingLot.parkingName.equals(parkingTicket.parkingName)){
                return managedParkingLot;
            }
        }

        throw new ParkingLotNotExistingException(parkingTicket.parkingName + " is not existing!");
    }

    public void setManagedParkingLots(List<ParkingLot> managedParkingLots) {
        this.managedParkingLots = managedParkingLots;
    }

    public List<ParkingLot> getManagedParkingLots() {
        return managedParkingLots;
    }
}
