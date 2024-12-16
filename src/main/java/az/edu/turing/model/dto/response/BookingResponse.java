package az.edu.turing.model.dto.response;

import java.util.Arrays;
import java.util.List;

public class BookingResponse {

    private long flightId;
    private String[] createdBy;
    private List<String[]> passengers;
    private boolean isActive;

    public BookingResponse(long flightId, String[] createdBy, boolean isActive, List<String[]> passengers) {
        this.flightId = flightId;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.passengers = passengers;
    }

    public List<String[]> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<String[]> passengers) {
        this.passengers = passengers;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public String[] getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String[] createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "flightId=" + flightId +
                ", createdBy=" + Arrays.toString(createdBy) +
                ", passengers=" + passengers +
                ", isActive=" + isActive +
                '}';
    }
}
