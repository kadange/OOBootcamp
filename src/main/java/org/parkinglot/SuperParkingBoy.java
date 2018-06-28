package org.parkinglot;

public class SuperParkingBoy extends ParkingBoy {
    SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot getAvailableParkingLot() throws NoParkingSpaceException {
        ParkingLot parkingLot = getParkingLotWithMostAvailableSpaceRation();


        if (parkingLot != null) {
            return parkingLot;
        }
        throw new NoParkingSpaceException("No Slots available on all parking lot");
    }

    private ParkingLot getParkingLotWithMostAvailableSpaceRation() {
        ParkingLot parkingLotWithMostAvailableSpaceRate = null;
        for (ParkingLot managedParkingLot : managedParkingLots) {
            if (managedParkingLot.isParkingLotFull()) {
                continue;
            }
            if (parkingLotWithMostAvailableSpaceRate == null || getSpaceRate(parkingLotWithMostAvailableSpaceRate) < getSpaceRate(managedParkingLot)) {
                parkingLotWithMostAvailableSpaceRate = managedParkingLot;
            }
        }

        return parkingLotWithMostAvailableSpaceRate;
    }

    private double getSpaceRate(ParkingLot parkingLot) {
        return parkingLot.getAvailableSlots()/parkingLot.getParkingLotSize();
    }
}
