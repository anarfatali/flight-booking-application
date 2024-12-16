package az.edu.turing.domain.dao.abstracts;

import az.edu.turing.domain.dao.DAO;
import az.edu.turing.domain.entity.FlightEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class FlightDAO implements DAO <FlightEntity, Long> {

    @Override
    public Collection<FlightEntity> findAll() {
        return List.of();
    }

    @Override
    public FlightEntity create(FlightEntity entity) {
        return null;
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveChanges() {

    }
}
