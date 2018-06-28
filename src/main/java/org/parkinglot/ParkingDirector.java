package org.parkinglot;

import java.util.Iterator;
import java.util.List;

import static java.lang.Math.*;

public class ParkingDirector {
    String report = "";


    public void generateParkingReport(ParkingManager parkingManager) {
        double parkingManagerOccupiedSlots = 0;
        double parkingManagerParkingLots = 0;
        double totalParkingLots = 0;
        double totalOccupiedSlots = 0;
        int parkingBoysLotSize = 0;
        int parkingBoysOccupiedSlot = 0;

        StringBuilder mgrParkingLotString = new StringBuilder();
        StringBuilder parkingBoyString = new StringBuilder();

        for (ParkingLot managerParkingLot : parkingManager.getManagedParkingLots()) {
            parkingManagerOccupiedSlots += managerParkingLot.getParkingLotSize()-managerParkingLot.getAvailableSlots();
            parkingManagerParkingLots += managerParkingLot.getParkingLotSize();

            int totalSlot = 0;
            int totalOccupiedSlot = 0;

            for(ParkingBoy parkingBoy : parkingManager.getParkingBoy()){
                parkingBoysLotSize = 0;
                parkingBoysOccupiedSlot = 0;
                StringBuilder parkingLotReport = new StringBuilder();
                for (ParkingLot parkingLot : parkingBoy.getManagedParkingLots()) {
                    parkingBoysLotSize += parkingLot.getParkingLotSize();
                    parkingBoysOccupiedSlot += (parkingLot.getParkingLotSize() - parkingLot.getAvailableSlots());
                    parkingLotReport.append("\t\tP ").append(round(parkingBoysOccupiedSlot)).append(" ").append(round(parkingLot.getParkingLotSize())).append("\n");
                }
                totalSlot += parkingBoysLotSize;
                totalOccupiedSlot += parkingBoysOccupiedSlot;
                parkingBoyString.append("\tB ").append(round(parkingBoysOccupiedSlot)).append(" ").append(round(parkingBoysLotSize)).append("\n");
                parkingBoyString.append(parkingLotReport);
            }
            totalOccupiedSlots = totalOccupiedSlot + parkingManagerOccupiedSlots;
            totalParkingLots = totalSlot + parkingManagerParkingLots;
            mgrParkingLotString.append("M" + " ").append(round(totalOccupiedSlots)).append(" ").append(round(totalParkingLots)).append("\n");
            mgrParkingLotString.append("\tP ").append(round(parkingManagerOccupiedSlots)).append(" ").append(round(parkingManagerParkingLots)).append("\n");
        }

        report = mgrParkingLotString.toString() + parkingBoyString;
    }


    public String getReport() {
        return report;
    }
}
