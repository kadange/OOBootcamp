package org.parkinglot;

public class NotEnoughParkingSpaceException extends Exception {
    private String message;

    public NotEnoughParkingSpaceException(String message) throws Exception {
        this.message = message;
        throwException();
    }

    private Exception throwException() throws Exception {
        throw new Exception(message);
    }
}
