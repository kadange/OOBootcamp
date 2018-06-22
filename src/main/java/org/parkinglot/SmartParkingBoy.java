package org.parkinglot;

public class SmartParkingBoy extends ParkingBoy {

    SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot getAvailableParkingLot() throws NoParkingSpaceException {
        ParkingLot parkingLot = getParkingLotListWithMostAvailableSlot();

        if (parkingLot != null) {
            return parkingLot;
        }
        throw new NoParkingSpaceException("No Slots available on all parking lot");
    }

    private ParkingLot getParkingLotListWithMostAvailableSlot() {
        ParkingLot parkingLotWithMostAvailableSpace = null;
        for (ParkingLot managedParkingLot : managedParkingLots) {
            if (managedParkingLot.isParkingLotFull()) {
                continue;
            }

            if (parkingLotWithMostAvailableSpace == null || parkingLotWithMostAvailableSpace.getAvailableSlots() < managedParkingLot.getAvailableSlots()) {
                parkingLotWithMostAvailableSpace = managedParkingLot;
            }
        }

        return parkingLotWithMostAvailableSpace;
    }
}
