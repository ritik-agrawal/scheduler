package com.assignment.scheduler.services;

import com.assignment.scheduler.Constants;
import com.assignment.scheduler.dto.AppointmentDetail;
import com.assignment.scheduler.dto.BookRequest;
import com.assignment.scheduler.dto.BookResponse;
import com.assignment.scheduler.dto.RescheduleRequest;
import com.assignment.scheduler.entity.Appointment;
import com.assignment.scheduler.entity.Operator;
import com.assignment.scheduler.exceptions.InvalidInputException;
import com.assignment.scheduler.repositories.AppointmentRepository;
import com.assignment.scheduler.repositories.OperatorRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class OperatorService {

    @Autowired private OperatorRepository operatorRepository;
    @Autowired private AppointmentRepository appointmentRepository;

    @PostConstruct
    public void setUp(){
        var allOperators = getAllOperators();
        if (allOperators.isEmpty()){
            log.info("Setting up Operators");
            operatorRepository.save(new Operator("ServiceOperator0"));
            operatorRepository.save(new Operator("ServiceOperator1"));
            operatorRepository.save(new Operator("ServiceOperator2"));
        }
    }

    public Map<String, List<AppointmentDetail>> fetchAllBookings(){
        var operators = getAllOperators();
        var ret = new HashMap<String, List<AppointmentDetail>>();
        for (Operator operator : operators){
            var slots = getAllSlots(operator.getAppointments());
            ret.put(operator.getName(), slots);
        }
        return ret;
    }

    public List<String> fetchOpenSlots(Integer request) throws InvalidInputException {
        var operator = getOperator(request);
        var appointments = operator.getAppointments();
        appointments.sort(Comparator.comparing(Appointment::getFrom));
        return getOpenSlots(appointments);
    }

    private List<String> getOpenSlots(List<Appointment> appointments){
        var ret = new ArrayList<String>();
        var fromList = getFromList(appointments);
        var len = fromList.size();
        var cur = 1;
        while (cur < len){
            var diff = fromList.get(cur) - fromList.get(cur-1);
            if (diff != 1){
                ret.add(getSlot((fromList.get(cur-1)+1), fromList.get(cur)));
            }
            cur++;
        }
        return ret;
    }

    private List<Integer> getFromList(List<Appointment> appointments){
        var ret = new ArrayList<Integer>();
        ret.add(0); //added for ease of calculation
        for (Appointment appointment : appointments){
            if (!appointment.isCancelled()){
                ret.add(appointment.getFrom());
            }
        }
        ret.add(24); //added for ease of calculation
        return ret;
    }

    public BookResponse bookSlot(BookRequest request) throws InvalidInputException {
        var operator = getOperator(request.operatorId());
        var appointment = new Appointment(
                operator, request.from(), request.to(), Constants.CUSTOMER
        );
        var appointments = operator.getAppointments();
        appointments.add(appointment);
        operator = operatorRepository.save(operator);
        return new BookResponse(operator.getId(), operator.getName(), request.from(), request.to());
    }

    public BookResponse reschedule(RescheduleRequest request) throws InvalidInputException {
        var appointment = getAppointment(request.appointmentId());
        var operator = appointment.getOperator();
        return null;
    }

    public void cancelAppointment(Integer id) throws InvalidInputException {
        var appointment = getAppointment(id);
        appointment.setCancelled(true);
        appointmentRepository.save(appointment);
    }

    public Operator getOperator(Integer id) throws InvalidInputException {
        var ret = operatorRepository.findById(id);
        if (ret.isPresent()){
            return ret.get();
        }
        throw new InvalidInputException(id, "operator id");
    }

    private List<Operator> getAllOperators(){
        return operatorRepository.findAll();
    }

    public Boolean isSlotAvailable(BookRequest request) throws InvalidInputException {
        var operator = getOperator(request.operatorId());
        var appointments = operator.getAppointments();
        return appointments.stream().noneMatch(ap -> Objects.equals(ap.getFrom(), request.from()));
    }

    public Appointment getAppointment(Integer id) throws InvalidInputException {
        var ret = appointmentRepository.findById(id);
        if (ret.isPresent() && !ret.get().isCancelled()){
            return ret.get();
        }
        throw new InvalidInputException(id, "appointment id");
    }

    private List<AppointmentDetail> getAllSlots(List<Appointment> appointments){
        var ret = new ArrayList<AppointmentDetail>();
        for (Appointment appointment : appointments){
            if (!appointment.isCancelled()) {
                ret.add(new AppointmentDetail(appointment.getId(), getSlot(appointment)));
            }
        }
        return ret;
    }

    private String getSlot(Appointment appointment){
        return getSlot(appointment.getFrom(), appointment.getTo());
    }

    private String getSlot(Integer from, Integer to){
        var ret = new StringBuilder();
        ret.append(from).append("-").append(to);
        return ret.toString();
    }

    public void validateSlot(Integer from, Integer to) throws InvalidInputException {
        if (from > to){
            var slot = getSlot(from, to);
            throw new InvalidInputException(slot, "Slot");
        }
        if (from <= 0){
            throw new InvalidInputException(from, "'from' value");
        }

        if (to > 24){
            throw new InvalidInputException(to, "'to' value");
        }
    }
}
