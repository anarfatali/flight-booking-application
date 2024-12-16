package az.edu.turing.exception;

public class PassengerNotFoundException extends NotFoundException {

    public PassengerNotFoundException() {
        super("Passenger not found");
    }
}
