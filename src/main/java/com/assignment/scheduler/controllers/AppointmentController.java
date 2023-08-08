package com.assignment.scheduler.controllers;

import com.assignment.scheduler.apis.*;
import com.assignment.scheduler.dto.*;
import com.assignment.scheduler.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/v1/appointment")
public class AppointmentController {

    @Autowired private BookAppointmentApi bookAppointmentApi;
    @Autowired private RescheduleAppointmentApi rescheduleAppointmentApi;
    @Autowired private CancelAppointmentApi cancelAppointmentApi;
    @Autowired private FetchBookingsApi fetchBookingsApi;
    @Autowired private OpenSlotsApi openSlotsApi;

    @GetMapping(path = "/all-bookings")
    private ResponseEntity<Map<String, List<AppointmentDetail>>> fetchAllBookings(){
        return ResponseEntity.ok(fetchBookingsApi.process());
    }

    @GetMapping(path = "/open-slots")
    private ResponseEntity<List<String>> fetchOpenSlots(@RequestParam("id") Integer request) throws InvalidInputException {
        return ResponseEntity.ok(openSlotsApi.process(request));
    }

    @PostMapping(path = "/book")
    private ResponseEntity<BookResponse> bookSlot(@RequestBody BookRequest request) throws InvalidInputException {
        return ResponseEntity.ok(bookAppointmentApi.process(request));
    }

    @PutMapping(path = "/reschedule")
    private ResponseEntity<BookResponse> reschedule(@RequestBody RescheduleRequest request) throws InvalidInputException {
        return ResponseEntity.ok(rescheduleAppointmentApi.process(request));
    }

    @DeleteMapping(path = "/cancel")
    private ResponseEntity<Response> cancel(@RequestParam("appointment_id") Integer id) throws InvalidInputException {
        return ResponseEntity.ok(cancelAppointmentApi.process(id));
    }
}
