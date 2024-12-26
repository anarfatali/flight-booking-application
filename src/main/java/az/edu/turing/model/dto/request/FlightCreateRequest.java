package az.edu.turing.model.dto.request;

import java.time.LocalDateTime;

public class FlightCreateRequest {

    private LocalDateTime departureTime;
    private String destinationPoint;
    private int totalSeats;

    public FlightCreateRequest(LocalDateTime departureTime, String destinationPoint, int totalSeats) {
        this.departureTime = departureTime;
        this.destinationPoint = destinationPoint;
        this.totalSeats = totalSeats;
    }

    public LocalDateTime getDepartureDate() {
        return departureTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}
