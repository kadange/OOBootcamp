package org.parkinglot;

public class NoCarParkedException extends Exception{

    private String message;

    public NoCarParkedException(String message) {
        this.message = message;
        throwException();
    }

    private Exception throwException() {
        return new Exception(message);
    }
}
