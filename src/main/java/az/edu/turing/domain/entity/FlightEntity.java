package az.edu.turing.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class FlightEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime departureDateTime;
    private String destinationPoint;
    private Integer totalSeats;
    private Integer freeSeats;

    public FlightEntity(LocalDateTime departureDateTime, String destinationPoint, Integer totalSeats, Integer freeSeats) {
        this.departureDateTime = departureDateTime;
        this.destinationPoint = destinationPoint;
        this.totalSeats = totalSeats;
        this.freeSeats = freeSeats;
    }

    public FlightEntity(Long id, LocalDateTime departureDateTime, String destinationPoint, Integer totalSeats, Integer freeSeats) {
        this.id = id;
        this.departureDateTime = departureDateTime;
        this.destinationPoint = destinationPoint;
        this.totalSeats = totalSeats;
        this.freeSeats = freeSeats;
    }

    public FlightEntity(LocalDateTime departureTime, String destinationPoint, Integer totalSeats) {
        this.departureDateTime = departureTime;
        this.destinationPoint = destinationPoint;
        this.totalSeats = totalSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightEntity that = (FlightEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(destinationPoint, that.destinationPoint) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destinationPoint);
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "id=" + id +
                ", departureDateTime=" + departureDateTime +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", totalSeats=" + totalSeats +
                ", freeSeats=" + freeSeats +
                '}';
    }
}
