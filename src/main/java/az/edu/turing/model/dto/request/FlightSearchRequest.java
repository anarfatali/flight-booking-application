package az.edu.turing.model.dto.request;

import java.time.LocalDate;

public class FlightSearchRequest {

    private String destinationPoint;
    private LocalDate date;
    private int numberOfSeats;

    public FlightSearchRequest(String destinationPoint, LocalDate date, int numberOfSeats) {
        this.destinationPoint = destinationPoint;
        this.date = date;
        this.numberOfSeats = numberOfSeats;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
