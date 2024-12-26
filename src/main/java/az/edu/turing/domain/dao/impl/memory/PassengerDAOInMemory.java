package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.abstracts.PassengerDAO;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.*;

public class PassengerDAOInMemory extends PassengerDAO {

    private static Map<Long, PassengerEntity> passengers;

    public PassengerDAOInMemory() {
        passengers = new HashMap<>();
    }

    private static long idCounter = 1;

    @Override
    public Optional<PassengerEntity> findByFirstNameAndLastName(String firstName, String lastName) {
        return passengers.
                values().
                stream().
                filter(f -> f.getFirstName().equals(firstName) && f.getLastName().equals(lastName)).
                findFirst();
    }

    @Override
    public Collection<PassengerEntity> findAll() {
        return new ArrayList<>(passengers.values());
    }

    @Override
    public PassengerEntity create(PassengerEntity entity) {
        Optional<PassengerEntity> passenger = findByFirstNameAndLastName(entity.getFirstName(), entity.getLastName());
        if (passenger.isPresent()) {
            return passenger.get();
        }
        entity.setId(idCounter++);
        passengers.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<PassengerEntity> getById(Long id) {
        return Optional.ofNullable(passengers.get(id));
    }

    @Override
    public void saveChanges() {

    }
}
