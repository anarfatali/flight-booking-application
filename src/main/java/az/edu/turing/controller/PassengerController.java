package az.edu.turing.controller;

import az.edu.turing.model.dto.PassengerDTO;
import az.edu.turing.service.PassengerService;

public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public PassengerDTO create(PassengerDTO request) {
        return passengerService.create(request);
    }
}
