package az.edu.turing.service.impl;

import az.edu.turing.model.dto.request.FlightCreateRequest;
import az.edu.turing.model.dto.request.FlightSearchRequest;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.FlightService;

import java.util.Collection;
import java.util.List;

public class FlightServiceImpl implements FlightService {


    @Override
    public FlightResponse create(FlightCreateRequest request) {
        return null;
    }

    @Override
    public Collection<FlightResponse> findAllWithinNext24Hours() {
        return List.of();
    }

    @Override
    public FlightResponse showInfo(long flightId) {
        return null;
    }

    @Override
    public Collection<FlightResponse> search(FlightSearchRequest request) {
        return List.of();
    }
}
