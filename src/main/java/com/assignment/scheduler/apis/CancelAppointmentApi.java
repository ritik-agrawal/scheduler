package com.assignment.scheduler.apis;

import com.assignment.scheduler.dto.Response;
import com.assignment.scheduler.exceptions.InvalidInputException;
import com.assignment.scheduler.services.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service ("cancelAppointmentApi")
@RequiredArgsConstructor
public class CancelAppointmentApi extends AbstractApi<Integer, Response>{

    private static final String SUCCESS = "Appointment with id %d has been cancelled successfully.";

    private final OperatorService service;

    @Override
    public Response process(Integer in) throws InvalidInputException {
        validate(in);
        service.cancelAppointment(in);
        return new Response(HttpStatus.OK.value(), String.format(SUCCESS, in));
    }

    private void validate(Integer in) throws InvalidInputException {
        validateInput(in);
        service.getAppointment(in);
    }
}
