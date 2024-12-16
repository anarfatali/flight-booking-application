package az.edu.turing.controller;

import az.edu.turing.model.dto.request.FlightCreateRequest;
import az.edu.turing.model.dto.request.FlightSearchRequest;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.FlightService;

import java.util.Set;

public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public FlightResponse create(FlightCreateRequest request) {
        return flightService.create(request);
    }

    public Set<FlightResponse> findAllWithinNext24Hours() {
        return (Set<FlightResponse>) flightService.findAllWithinNext24Hours();
    }

    public FlightResponse showInfo(long id) {
        return flightService.showInfo(id);
    }

    public Set<FlightResponse> search(FlightSearchRequest request) {
        return (Set<FlightResponse>) flightService.search(request);
    }
}
