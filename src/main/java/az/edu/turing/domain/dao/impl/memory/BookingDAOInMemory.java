package az.edu.turing.domain.dao.impl.memory;

import az.edu.turing.domain.dao.abstracts.BookingDAO;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.exception.BookingNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class BookingDAOInMemory extends BookingDAO {

    Map<Long, BookingEntity> bookings = new HashMap<>();

    private static long idCounter = 1;

    @Override
    public Set<BookingEntity> findAllByPassengerId(long passengerId) {
        return bookings.
                values().
                stream().
                filter(f -> f.getCreatedBy().getId() == passengerId ||
                        f.getPassengers()
                                .stream()
                                .anyMatch(p -> p.getId() == passengerId))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean cancelBooking(long bookingId) {
        if (existsById(bookingId)) throw new BookingNotFoundException("Booking with id " + bookingId + " not found");
        if (!bookings.get(bookingId).getActive()) return false;
        bookings.get(bookingId).setActive(false);
        return true;
    }

    @Override
    public boolean existsById(long id) {
        return bookings.containsKey(id);
    }

    @Override
    public Collection<BookingEntity> findAll() {
        return new ArrayList<>(bookings.values());
    }

    @Override
    public BookingEntity create(BookingEntity entity) {
        entity.setId(idCounter++);
        bookings.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        return Optional.ofNullable(bookings.get(id));
    }

    @Override
    public void saveChanges() {

    }
}
