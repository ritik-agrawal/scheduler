package com.assignment.scheduler.apis;

import com.assignment.scheduler.exceptions.InvalidInputException;

import java.util.Objects;

public abstract class AbstractApi<I, O> {

    public abstract O process(I in) throws InvalidInputException;


    protected void validateInput(I in) throws InvalidInputException {
        if (Objects.isNull(in)){
            throw new InvalidInputException();
        }
    }
}
