package az.edu.turing.domain.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BookingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private FlightEntity flight;
    private PassengerEntity createdBy;
    private List<PassengerEntity> passengers;
    private Boolean isActive;

    public BookingEntity(Long id, FlightEntity flight, PassengerEntity createdBy, Boolean isActive) {
        this.id = id;
        this.flight = flight;
        this.createdBy = createdBy;
        this.isActive = isActive;
    }

    public BookingEntity(FlightEntity flight, PassengerEntity createdBy, List<PassengerEntity> passengers) {
        this.flight = flight;
        this.createdBy = createdBy;
        this.passengers = passengers;
        this.isActive = true;
    }

    public BookingEntity(Long id, FlightEntity flight, PassengerEntity createdBy, List<PassengerEntity> passengers) {
        this.id = id;
        this.flight = flight;
        this.createdBy = createdBy;
        this.passengers = passengers;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public PassengerEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(PassengerEntity createdBy) {
        this.createdBy = createdBy;
    }

    public List<PassengerEntity> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerEntity> passengers) {
        this.passengers = passengers;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookingEntity booking = (BookingEntity) object;
        return Objects.equals(id, booking.id) && Objects.equals(flight, booking.flight) && Objects.equals(createdBy, booking.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, createdBy);
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", flight=" + flight +
                ", createdBy=" + createdBy +
                ", passengers=" + passengers +
                ", isActive=" + isActive +
                '}';
    }
}