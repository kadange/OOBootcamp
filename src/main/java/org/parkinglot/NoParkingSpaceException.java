package org.parkinglot;

public class NoParkingSpaceException extends Exception{
    private String message;

    public NoParkingSpaceException(String message) throws Exception {
        this.message = message;
        throwException();
    }

    private Exception throwException() throws Exception {
        throw new Exception(message);
    }
}
