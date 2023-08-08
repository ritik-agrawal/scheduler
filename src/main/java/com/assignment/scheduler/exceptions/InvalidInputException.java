package com.assignment.scheduler.exceptions;

public class InvalidInputException extends Exception{

    private static final String message = "Invalid %s : %s";

    private static final String NO_INPUT = "Please provide required details.";

    public InvalidInputException(){
        super(NO_INPUT);
    }

    public InvalidInputException(Integer id, String input){
        super(String.format(message, input, id));
    }

    public InvalidInputException(String str, String input){
        super(String.format(message, input, str));
    }
}
