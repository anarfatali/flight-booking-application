package az.edu.turing.domain.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class BookingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private FlightEntity flight;
    private BookingEntity booking;
    private PassengerEntity createdBy;
    private List<PassengerEntity> passengers;
    private Boolean isActive;

    public BookingEntity(long id, FlightEntity flight, PassengerEntity createdBy, List<PassengerEntity> passengers, Boolean isActive) {
        this.id = id;
        this.flight = flight;
        this.createdBy = createdBy;
        this.passengers = passengers;
        this.isActive = true;
    }

    public BookingEntity(long id, FlightEntity flight, PassengerEntity createdBy, Boolean isActive) {
        this.id = id;
        this.flight = flight;
        this.createdBy = createdBy;
        this.isActive = isActive;
    }

    public BookingEntity(FlightEntity flight, PassengerEntity createdBy, List<PassengerEntity> passengers, Boolean isActive) {
        this.flight = flight;
        this.createdBy = createdBy;
        this.passengers = passengers;
        this.isActive = true;
    }

    public FlightEntity getFlight() {
        return flight;
    }

    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return id == that.id && Objects.equals(flight, that.flight) && Objects.equals(booking, that.booking) && Objects.equals(createdBy, that.createdBy) && Objects.equals(passengers, that.passengers) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, booking, createdBy, passengers, isActive);
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id=" + id +
                ", flight=" + flight +
                ", booking=" + booking +
                ", createdBy=" + createdBy +
                ", passengers=" + passengers +
                ", isActive=" + isActive +
                '}';
    }
}
