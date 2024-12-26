package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.abstracts.FlightDAO;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.request.FlightSearchRequest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlightDAOInMemory extends FlightDAO {

    private static final Map<Long, FlightEntity> flights = new HashMap<>();

    private static long idCounter = 1;

    @Override
    public List<FlightEntity> findAllWithInNext24Hours() {
        return flights.
                values().
                stream().
                filter(p -> p.getDepartureDateTime().isAfter(LocalDateTime.now()) &&
                        p.getDepartureDateTime().isBefore(LocalDateTime.now().plusHours(24))).
                collect(Collectors.toList());
    }

    @Override
    public List<FlightEntity> search(FlightSearchRequest request) {
        return flights.
                values().
                stream().
                filter(p -> p.getDepartureDateTime().equals(request.getDestinationPoint()) &&
                        p.getDepartureDateTime().toLocalDate().equals(request.getDate()) &&
                        p.getFreeSeats() >= request.getNumberOfSeats())
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightEntity> findAll() {
        return new ArrayList<>(flights.values());
    }

    @Override
    public FlightEntity create(FlightEntity entity) {
        entity.setId(idCounter++);
        flights.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        return Optional.ofNullable(flights.get(id));
    }

    @Override
    public void saveChanges() {

    }
}
