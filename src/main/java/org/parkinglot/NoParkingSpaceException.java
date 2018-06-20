package org.parkinglot;

public class NoParkingSpaceException extends Exception{
    private String message;

    public NoParkingSpaceException(String message) {
        this.message = message;
    }
}
