package com.assignment.scheduler.dto;

public record BookResponse(
        Integer operatorId,
        String operatorName,
        Integer from,
        Integer to
) {
}
