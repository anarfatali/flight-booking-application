package az.edu.turing.model.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightResponse {

    private long flightId;
    private LocalTime departureTime;
    private LocalDate departureDate;
    private String destinationPoint;
    private int freeSeats;

    public FlightResponse(long flightId, LocalTime departureTime, LocalDate departureDate, String destinationPoint, int freeSeats) {
        this.flightId = flightId;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.destinationPoint = destinationPoint;
        this.freeSeats = freeSeats;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "FlightResponse{" +
                "flightId=" + flightId +
                ", departureTime='" + departureTime + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", freeSeats=" + freeSeats +
                '}';
    }
}
