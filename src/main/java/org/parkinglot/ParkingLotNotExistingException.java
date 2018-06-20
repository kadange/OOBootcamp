package org.parkinglot;

public class ParkingLotNotExistingException extends Exception {
    private String message;

    public ParkingLotNotExistingException(String message) {
        this.message = message;
    }
}
