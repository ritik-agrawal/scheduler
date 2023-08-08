package com.assignment.scheduler.apis;

import com.assignment.scheduler.dto.AppointmentDetail;
import com.assignment.scheduler.services.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service ("fetchBookingsApi")
@RequiredArgsConstructor
public class FetchBookingsApi {

    private final OperatorService service;

    public Map<String, List<AppointmentDetail>> process(){
        return service.fetchAllBookings();
    }
}
