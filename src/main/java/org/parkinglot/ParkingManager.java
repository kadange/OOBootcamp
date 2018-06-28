package org.parkinglot;

import java.util.List;

public class ParkingManager extends ParkingBoy{

    private List<ParkingBoy> parkingBoys;

    public ParkingManager(ParkingLot... parkingLots){
        super(parkingLots);
    }

    public void manageParkingBoy(List<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public List<ParkingBoy> getParkingBoy() {
        return this.parkingBoys;
    }

    public List<Ticket> managerPark(List<Car> cars) throws Exception {
        List<ParkingBoy> parkingBoys = getParkingBoy();
        if (parkingBoys == null || parkingBoys.isEmpty()){
            return park(cars);
        }
        for (ParkingBoy parkingBoy : parkingBoys) {
            if(!parkingBoy.getAvailableParkingLot().isParkingLotFull()){
                return parkingBoy.park(cars);
            }
        }
        return null;
    }
}
