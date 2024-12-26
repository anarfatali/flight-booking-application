package az.edu.turing.domain.dao.impl.postgres;

import az.edu.turing.config.DatabaseConfig;
import az.edu.turing.domain.dao.abstracts.BookingDAO;
import az.edu.turing.domain.dao.abstracts.FlightDAO;
import az.edu.turing.domain.dao.abstracts.PassengerDAO;
import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.exception.BookingNotFoundException;

import java.sql.*;
import java.util.*;

import static az.edu.turing.model.dto.constants.DatabaseQueries.*;

public class BookingDAOPostgres extends BookingDAO {

    private final DatabaseConfig databaseConfig;
    private final FlightDAO flightDAO = new FlightDAOPostgres();
    private final PassengerDAO passengerDAO = new PassengerDAOPostgres();

    public BookingDAOPostgres() {
        this.databaseConfig = new DatabaseConfig();
        try (Connection connection = databaseConfig.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_BOOKINGS);
            statement.executeUpdate(CREATE_TABLE_BOOKINGS_PASSENGERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<BookingEntity> findAllByPassengerId(long passengerId) {
        String query = "SELECT b.id, b.flight_id, b.created_by, b.is_active " +
                "FROM bookings b" +
                "         LEFT JOIN bookings_passengers bp on b.id = bp.booking_id" +
                "         LEFT JOIN passengers p on bp.passenger_id = p.id " +
                "where created_by = ? OR p.id = ?;";
        Set<BookingEntity> bookings = new HashSet<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, passengerId);
            ps.setLong(2, passengerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookings.add(resultSetToEntity(rs));
            }

            return resultSetToEntity(bookings, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    @Override
    public boolean cancelBooking(long bookingId) {
        if (!existsById(bookingId)) throw new BookingNotFoundException("Booking with id " + bookingId + " not found");
        String query = "UPDATE bookings SET is_active = false WHERE id = ?;";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bookingId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean existsById(long id) {
        return false;
    }

    @Override
    public Set<BookingEntity> findAll() {
        Set<BookingEntity> bookings = new HashSet<>();
        String query = "SELECT * FROM bookings;";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookings.add(resultSetToEntity(resultSet));
            }

            return resultSetToEntity(bookings, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    private Set<BookingEntity> resultSetToEntity(Set<BookingEntity> bookings, Connection conn) throws SQLException {
        String query = "SELECT passenger_id FROM bookings_passengers where booking_id=?;";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            for (BookingEntity booking : bookings) {
                List<PassengerEntity> passengers = new ArrayList<>();
                ps.setLong(1, booking.getId());
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()) {
                    passengers.add(passengerDAO.getById(rs2.getLong(1)).get());
                }
                booking.setPassengers(passengers);
            }
        }
        return bookings;
    }

    private BookingEntity resultSetToEntity(ResultSet rs) throws SQLException {
        return new BookingEntity(
                rs.getLong("id"),
                flightDAO.getById(rs.getLong("flight_id")).get(),
                passengerDAO.getById(rs.getLong("created_by")).get(),
                rs.getBoolean("is_active")
        );
    }

    @Override
    public BookingEntity create(BookingEntity entity) {
        String query1 = "INSERT INTO bookings (flight_id, created_by, is_active) VALUES (?, ?, ?) RETURNING id";
        String query2 = "INSERT INTO bookings_passengers VALUES (?, ?)";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement pst1 = connection.prepareStatement(query1);
             PreparedStatement pst2 = connection.prepareStatement(query2)) {

            pst1.setLong(1, entity.getFlight().getId());
            pst1.setLong(2, entity.getCreatedBy().getId());
            pst1.setBoolean(3, entity.getActive());
            ResultSet rs = pst1.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getLong(1));
            }

            pst2.setLong(1, entity.getId());
            for (PassengerEntity passenger : entity.getPassengers()) {
                pst2.setLong(2, passenger.getId());
                pst2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        String query = "SELECT * FROM bookings WHERE id = ?";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(resultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void saveChanges() {

    }
}
