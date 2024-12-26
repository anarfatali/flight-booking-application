package az.edu.turing.domain.dao.impl.postgres;

import az.edu.turing.config.DatabaseConfig;
import az.edu.turing.domain.dao.abstracts.FlightDAO;
import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.request.FlightSearchRequest;

import java.sql.*;
import java.util.*;

import static az.edu.turing.model.dto.constants.DatabaseQueries.CREATE_TABLE_FLIGHTS;

public class FlightDAOPostgres extends FlightDAO {

    private final DatabaseConfig databaseConfig;

    public FlightDAOPostgres() {
        this.databaseConfig = new DatabaseConfig();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(CREATE_TABLE_FLIGHTS)) {

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<FlightEntity> findAllWithInNext24Hours() {
        String query = "SELECT * FROM flights WHERE departure_datetime BETWEEN NOW() AND NOW() + INTERVAL '1 DAY';";
        Set flights = new HashSet<>();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flights.add(resultSetToEntity(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public Set<FlightEntity> search(FlightSearchRequest request) {
        String query = "SELECT * FROM flights WHERE destination_point = ? AND departure_datetime::DATE = ?::DATE AND " +
                "free_seats >= ?;";
        Set<FlightEntity> flights = new HashSet<>();

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, request.getDestinationPoint());
            ps.setTimestamp(2, Timestamp.valueOf(request.getDate().atStartOfDay()));
            ps.setInt(3, request.getNumberOfSeats());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flights.add(resultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public Set<FlightEntity> findAll() {
        String query = "SELECT * FROM flights;";
        Set<FlightEntity> flights = new HashSet<>();
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flights.add(resultSetToEntity(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    private FlightEntity resultSetToEntity(ResultSet rs) throws SQLException {
        return new FlightEntity(
                rs.getLong("id"),
                rs.getTimestamp("departure_datetime").toLocalDateTime(),
                rs.getString("destination_point"),
                rs.getInt("total_seats"),
                rs.getInt("free_seats")
        );
    }

    @Override
    public FlightEntity create(FlightEntity entity) {
        String query = "INSERT INTO flights (departure_datetime, destination_point, total_seats, free_seats)" +
                "values (?, ?, ?, ?) RETURNING *";

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setTimestamp(1, Timestamp.valueOf(entity.getDepartureDateTime()));
            ps.setString(2, entity.getDestinationPoint());
            ps.setInt(3, entity.getTotalSeats());
            ps.setInt(4, entity.getFreeSeats());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = resultSetToEntity(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        String query = "SELECT * FROM flights WHERE id = ?";

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
