package az.edu.turing.service.impl;

import az.edu.turing.model.dto.request.BookingCreateRequest;
import az.edu.turing.model.dto.response.BookingResponse;
import az.edu.turing.service.BookingService;

import java.util.Collection;
import java.util.List;

public class BookingServiceImpl implements BookingService {


    @Override
    public BookingResponse create(BookingCreateRequest request) {
        return null;
    }

    @Override
    public boolean cancel(long id) {
        return false;
    }

    @Override
    public Collection<BookingResponse> findAllByPassengerNameAndLastName(String firstName, String lastName) {
        return List.of();
    }
}
