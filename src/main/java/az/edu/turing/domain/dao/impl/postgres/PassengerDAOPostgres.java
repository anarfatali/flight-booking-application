package az.edu.turing.domain.dao.impl.postgres;

import az.edu.turing.config.DatabaseConfig;
import az.edu.turing.domain.dao.abstracts.PassengerDAO;
import az.edu.turing.domain.entity.PassengerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static az.edu.turing.model.dto.constants.DatabaseQueries.*;

public class PassengerDAOPostgres extends PassengerDAO {

    private final DatabaseConfig databaseConfig;

    public PassengerDAOPostgres() {
        this.databaseConfig = new DatabaseConfig();
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement cs = connection.prepareStatement(CREATE_TABLE_PASSENGERS)) {
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PassengerEntity> findByFirstNameAndLastName(String firstName, String lastName) {
        return Optional.empty();
    }

    @Override
    public Collection<PassengerEntity> findAll() {
        String query = "SELECT * FROM passengers;";
        Set<PassengerEntity> passengers = new HashSet<>();
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                passengers.add(resultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    private PassengerEntity resultSetToEntity(ResultSet rs) throws SQLException {
        return new PassengerEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("last_name")
        );
    }

    @Override
    public PassengerEntity create(PassengerEntity entity) {
        return null;
    }

    @Override
    public Optional<PassengerEntity> getById(Long id) {
        String query = "SELECT * FROM passengers WHERE id = ?;";

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
