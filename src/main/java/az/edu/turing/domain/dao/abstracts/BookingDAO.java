package az.edu.turing.domain.dao.abstracts;

import az.edu.turing.domain.dao.DAO;
import az.edu.turing.domain.entity.BookingEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class BookingDAO implements DAO <BookingEntity, Long> {


    @Override
    public Collection<BookingEntity> findAll() {
        return List.of();
    }

    @Override
    public BookingEntity create(BookingEntity entity) {
        return null;
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveChanges() {

    }
}
