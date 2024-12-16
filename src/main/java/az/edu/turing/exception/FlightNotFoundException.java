package az.edu.turing.exception;

public class FlightNotFoundException extends NotFoundException {

    public FlightNotFoundException() {
        super("Flight not found");
    }
}
