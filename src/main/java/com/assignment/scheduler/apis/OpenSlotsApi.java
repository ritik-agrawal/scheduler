package com.assignment.scheduler.apis;

import com.assignment.scheduler.exceptions.InvalidInputException;
import com.assignment.scheduler.services.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service ("openSlotsApi")
@RequiredArgsConstructor
public class OpenSlotsApi extends AbstractApi<Integer, List<String>>{

    private final OperatorService service;


    @Override
    public List<String> process(Integer in) throws InvalidInputException {
        validate(in);
        return service.fetchOpenSlots(in);
    }

    private void validate(Integer request) throws InvalidInputException {
        validateInput(request);
        service.getOperator(request);
    }
}
