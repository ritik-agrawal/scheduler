package com.assignment.scheduler.dto;

public record RescheduleRequest(
        Integer appointmentId,
        Integer from,
        Integer to
) {
}
