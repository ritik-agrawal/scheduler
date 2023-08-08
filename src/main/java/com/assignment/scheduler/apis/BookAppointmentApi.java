package com.assignment.scheduler.apis;

import com.assignment.scheduler.dto.BookRequest;
import com.assignment.scheduler.dto.BookResponse;
import com.assignment.scheduler.exceptions.InvalidInputException;
import com.assignment.scheduler.services.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service ("bookAppointmentApi")
@Slf4j
public class BookAppointmentApi extends AbstractApi <BookRequest, BookResponse>{

    @Autowired
    private OperatorService service;

    @Override
    public BookResponse process(BookRequest request) throws InvalidInputException {
        validate(request);
        return service.bookSlot(request);
    }

    private void validate(BookRequest request) throws InvalidInputException {
        validateInput(request);
        service.getOperator(request.operatorId());
        service.validateSlot(request.from(), request.to());
        if(!service.isSlotAvailable(request)){
            var slot = new StringBuilder();
            slot.append(request.from()).append("-").append(request.to());
            throw new InvalidInputException(slot.toString(), "Slot(already booked)");
        }
    }

}
