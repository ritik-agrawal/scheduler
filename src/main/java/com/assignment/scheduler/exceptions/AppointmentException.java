package com.assignment.scheduler.exceptions;

public class AppointmentException extends Exception{

    private static final String MESSAGE = "No appointments booked.";
    public AppointmentException(){
        super(MESSAGE);
    }
}
