package az.edu.turing.service.impl;

import az.edu.turing.domain.dao.abstracts.BookingDAO;
import az.edu.turing.domain.dao.abstracts.FlightDAO;
import az.edu.turing.domain.dao.abstracts.PassengerDAO;
import az.edu.turing.domain.dao.impl.postgres.FlightDAOPostgres;
import az.edu.turing.domain.dao.impl.postgres.PassengerDAOPostgres;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.BookingNotFoundException;
import az.edu.turing.exception.PassengerNotFoundException;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.request.BookingCreateRequest;
import az.edu.turing.model.dto.response.BookingResponse;
import az.edu.turing.service.BookingService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private final BookingDAO bookingDAO;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
        bookingMapper = new BookingMapper();
    }

    @Override
    public BookingResponse create(BookingCreateRequest request) {
        if (request.getPassengers().isEmpty()) throw new PassengerNotFoundException("Passengers not found");
        FlightDAO flightDAO = new FlightDAOPostgres();
        PassengerDAO passengerDAO = new PassengerDAOPostgres();
        BookingEntity booking = new BookingEntity(
                flightDAO.getById(request.getFlightId()).orElseThrow(BookingNotFoundException::new),
                passengerDAO.findByFirstNameAndLastName(request.getCreatedBy()[0], request.getCreatedBy()[1])
                        .orElse(passengerDAO.create(new PassengerEntity(
                                request.getCreatedBy()[0], request.getCreatedBy()[1]
                        ))),
                request.getPassengers()
                        .stream()
                        .map(p -> passengerDAO.findByFirstNameAndLastName(p[0], p[1])
                                .orElse(passengerDAO.create(new PassengerEntity(p[0], p[1]))))
                        .collect(Collectors.toList())
        );
        BookingEntity savedBooking = bookingDAO.create(booking);
        return bookingMapper.toResponse(savedBooking);
    }

    @Override
    public boolean cancel(long id) {
        return bookingDAO.cancelBooking(id);
    }

    @Override
    public Set<BookingResponse> findAllByPassengerNameAndLastName(String firstName, String lastName) {
        PassengerDAO passengerDAO = new PassengerDAOPostgres();
        Optional<PassengerEntity> passenger = passengerDAO.findByFirstNameAndLastName(firstName, lastName);
        if (passenger.isPresent()) {
            return bookingDAO.
                    findAllByPassengerId(passenger.get().getId()).
                    stream().map(bookingMapper::toResponse).collect(Collectors.toSet());
        }
        throw new PassengerNotFoundException("The passenger " + firstName + " " + lastName + " not found");
    }
}
