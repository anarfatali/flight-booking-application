package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.abstracts.PassengerDAO;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.PassengerDTO;
import az.edu.turing.service.PassengerService;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerDAO passengerDAO;
    private final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
        this.passengerMapper = new PassengerMapper();
    }

    @Override
    public PassengerDTO create(PassengerDTO request) {
        PassengerEntity passenger = new PassengerEntity(request.getFirstname(), request.getLastname());
        PassengerEntity savedPassenger = passengerDAO.create(passenger);
        return passengerMapper.toResponse(savedPassenger);
    }
}
