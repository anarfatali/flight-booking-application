package az.edu.turing.exception;

public class BookingNotFoundException extends NotFoundException {

    public BookingNotFoundException() {
        super("Booking not found");
    }
}
