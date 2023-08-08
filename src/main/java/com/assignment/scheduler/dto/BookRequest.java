package com.assignment.scheduler.dto;

import com.assignment.scheduler.entity.Operator;

import java.util.List;

public record BookRequest(
        Integer operatorId,
        Integer from,
        Integer to
) {
}
