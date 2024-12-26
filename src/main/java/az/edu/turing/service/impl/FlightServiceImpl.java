package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.abstracts.FlightDAO;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.exception.FlightNotFoundException;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.request.FlightCreateRequest;
import az.edu.turing.model.dto.request.FlightSearchRequest;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.FlightService;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    private final FlightDAO flightDAO;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
        flightMapper = new FlightMapper();
    }

    @Override
    public FlightResponse create(FlightCreateRequest request) {
        FlightEntity flightEntity = new FlightEntity(
                request.getDepartureTime(),
                request.getDestinationPoint(),
                request.getTotalSeats()
        );
        FlightEntity savedFlight = flightDAO.create(flightEntity);
        return flightMapper.toResponse(savedFlight);
    }

    @Override
    public Collection<FlightResponse> findAllWithinNext24Hours() {
        return flightDAO.findAllWithInNext24Hours()
                .stream()
                .map(flightMapper::toResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public FlightResponse showInfo(long flightId) {
        Optional<FlightEntity> flight = flightDAO.getById(flightId);
        if (flight.isPresent()) {
            return flightMapper.toResponse(flight.get());
        }
        throw new FlightNotFoundException("Flight with id " + flightId + " not found");
    }

    @Override
    public Set<FlightResponse> search(FlightSearchRequest request) {
        return flightDAO.search(request)
                .stream()
                .map(flightMapper::toResponse)
                .collect(Collectors.toSet());
    }
}
