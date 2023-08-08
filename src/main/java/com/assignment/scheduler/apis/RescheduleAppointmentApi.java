package com.assignment.scheduler.apis;

import com.assignment.scheduler.dto.BookRequest;
import com.assignment.scheduler.dto.BookResponse;
import com.assignment.scheduler.dto.RescheduleRequest;
import com.assignment.scheduler.exceptions.InvalidInputException;
import com.assignment.scheduler.services.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service ("rescheduleAppointmentApi")
@RequiredArgsConstructor
public class RescheduleAppointmentApi extends AbstractApi<RescheduleRequest, BookResponse>{

    private final OperatorService service;
    private final CancelAppointmentApi cancelAppointmentApi;
    private final BookAppointmentApi bookAppointmentApi;

    @Override
    public BookResponse process(RescheduleRequest in) throws InvalidInputException {
        validate(in);
        cancelAppointmentApi.process(in.appointmentId());
        var appointment = service.getAppointment(in.appointmentId());
        var bookRequest = new BookRequest(appointment.getOperator().getId(), in.from(), in.to());
        return bookAppointmentApi.process(bookRequest);
    }

    private void validate(RescheduleRequest in) throws InvalidInputException {
        validateInput(in);
        var appointment = service.getAppointment(in.appointmentId());
        var bookRequest = new BookRequest(appointment.getOperator().getId(), in.from(), in.to());
        service.isSlotAvailable(bookRequest);
    }


}
