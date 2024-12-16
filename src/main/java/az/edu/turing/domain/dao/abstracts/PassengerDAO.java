package az.edu.turing.domain.dao.abstracts;

import az.edu.turing.domain.dao.DAO;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class PassengerDAO implements DAO <PassengerEntity, Long> {


    @Override
    public Collection<PassengerEntity> findAll() {
        return List.of();
    }

    @Override
    public PassengerEntity create(PassengerEntity entity) {
        return null;
    }

    @Override
    public Optional<PassengerEntity> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveChanges() {

    }
}
