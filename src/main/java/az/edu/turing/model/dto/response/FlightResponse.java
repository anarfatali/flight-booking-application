package az.edu.turing.model.dto.response;

public class FlightResponse {

    private long flightId;
    private String departureTime;
    private String departureDate;
    private String destinationPoint;
    private int freeSeats;

    public FlightResponse(long flightId, String departureTime, String departureDate, String destinationPoint, int freeSeats) {
        this.flightId = flightId;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.destinationPoint = destinationPoint;
        this.freeSeats = freeSeats;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
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
