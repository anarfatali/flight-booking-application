package az.edu.turing.mapper;

import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.model.dto.PassengerDTO;

public class PassengerMapper {

    public PassengerDTO toResponse(PassengerEntity entity) {
        return new PassengerDTO(
                entity.getFirstName(),
                entity.getLastName()
        );
    }
}
