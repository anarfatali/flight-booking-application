package az.edu.turing.domain.dao.abstracts;

import az.edu.turing.domain.dao.DAO;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.request.FlightSearchRequest;

import java.util.Collection;

public abstract class FlightDAO implements DAO<FlightEntity, Long> {

    public abstract Collection<FlightEntity> findAllWithInNext24Hours();

    public abstract Collection<FlightEntity> search(FlightSearchRequest request);
}
