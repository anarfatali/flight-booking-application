package az.edu.turing.domain.dao.abstracts;

import az.edu.turing.domain.dao.DAO;
import az.edu.turing.domain.entity.PassengerEntity;

import java.util.Optional;

public abstract class PassengerDAO implements DAO<PassengerEntity, Long> {

    public abstract Optional<PassengerEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
