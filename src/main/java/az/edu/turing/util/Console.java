package az.edu.turing.util;

import az.edu.turing.controller.BookingController;
import az.edu.turing.controller.FlightController;
import az.edu.turing.controller.PassengerController;
import az.edu.turing.domain.dao.abstracts.BookingDAO;
import az.edu.turing.domain.dao.abstracts.FlightDAO;
import az.edu.turing.domain.dao.abstracts.PassengerDAO;
import az.edu.turing.domain.dao.impl.memory.BookingDAOInMemory;
import az.edu.turing.domain.dao.impl.memory.FlightDAOInMemory;
import az.edu.turing.domain.dao.impl.memory.PassengerDAOInMemory;
import az.edu.turing.domain.dao.impl.postgres.BookingDAOPostgres;
import az.edu.turing.domain.dao.impl.postgres.FlightDAOPostgres;
import az.edu.turing.domain.dao.impl.postgres.PassengerDAOPostgres;
import az.edu.turing.model.dto.constants.AppConstants;
import az.edu.turing.model.dto.request.BookingCreateRequest;
import az.edu.turing.model.dto.request.FlightCreateRequest;
import az.edu.turing.model.dto.request.FlightSearchRequest;
import az.edu.turing.model.dto.response.BookingResponse;
import az.edu.turing.model.dto.response.FlightResponse;
import az.edu.turing.service.BookingService;
import az.edu.turing.service.FlightService;
import az.edu.turing.service.PassengerService;
import az.edu.turing.service.impl.BookingServiceImpl;
import az.edu.turing.service.impl.FlightServiceImpl;
import az.edu.turing.service.impl.PassengerServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class Console {

    private static final Logger log = Logger.getLogger(Console.class.getName());

    private static final PassengerDAO passengerDAO =
        new PassengerDAOInMemory();
//        new PassengerDAOFile();
//            new PassengerDAOPostgres();


    private static final FlightDAO flightDAO =
            new FlightDAOInMemory();
//            new FlightDAOFile();
//            new FlightDAOPostgres();


    private static final BookingDAO bookingDAO =
            new BookingDAOInMemory();
//            new BookingDAOFile();
//            new BookingDAOPostgres();

    private static final FlightService flightService = new FlightServiceImpl(flightDAO);
    private static final BookingService bookingService = new BookingServiceImpl(bookingDAO);
    private static final PassengerService passengerService = new PassengerServiceImpl(passengerDAO);

    private static final PassengerController passengerController = new PassengerController(passengerService);
    private static final FlightController flightController = new FlightController(flightService);
    private static final BookingController bookingController = new BookingController(bookingService);

    public void run() {
//        createFlights();
        showCommandList();
        while (true) {
            int command = getValidInt("Enter a command: ");
            switch (command) {
                case 0:
                    saveChanges();
                    return;
                case 1:
                    showOnlineBoard();
                    break;
                case 2:
                    showFlightInfo();
                    break;
                case 3:
                    searchAndBookFlight();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    showMyFlights();
                    break;
                default:
                    System.out.println(("Please enter a valid command"));
            }
        }
    }

    private void searchFlight() {
        Scanner sc = new Scanner(System.in);
        System.out.print(("Enter destination point: "));
        String destinationPoint = sc.nextLine();
        int day = getValidInt("Enter the day: ");
        int month = getValidInt("Enter the month: ");
        int year = getValidInt("Enter the year: ");
        int numberOfPassengers = getValidInt("Enter the number of passengers: ");
        LocalDate date = LocalDate.of(year, month, day);
        Set<FlightResponse> flights = flightController.search(new FlightSearchRequest(
                destinationPoint, date, numberOfPassengers));
        if (flights.isEmpty()) log.info("No flights found");
        else displayFlights(flights);
    }

    private void book(long flightId) {
        String[] createdBy = getName("Enter your name and surname: ").split(" ");
        int numberOfPassengers = getValidInt("Enter number of passengers: ");
        List<String[]> passengers = new ArrayList<>(numberOfPassengers);
        for (int i = 0; i < numberOfPassengers; i++) {
            passengers.add(getName("Enter passenger name and surname: ").split(" "));
        }
        bookingController.create(new BookingCreateRequest(
                createdBy,
                flightId,
                passengers
        ));
        log.info("Booking created");
    }


    private void searchAndBookFlight() {
        searchFlight();
        long flightId = getValidId("Enter flight id or press '0' to return main menu: ");
        if (flightId == 0) return;
        book(flightId);
    }

    private void saveChanges() {
        passengerDAO.saveChanges();
        flightDAO.saveChanges();
        bookingDAO.saveChanges();
    }

    private void showMyFlights() {
        String[] fullName = getName("Enter your full name: ").split(" ");
        String name = fullName[0];
        String lastName = fullName[1];
        Set<BookingResponse> response = bookingController.findAllByPassengerNameAndLastName(name, lastName);
        printBookingHeader();
        response.forEach(System.out::println);
    }

    private void cancelBooking() {
        long bookingId = getValidId("Enter booking id: ");
        try {
            if (bookingController.cancel(bookingId)) {
                log.info("Booking cancelled.");
            }
            log.info("Booking is not active.");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    private void showFlightInfo() {
        while (true) {
            long flightId = getValidId("Enter flight id: ");
            try {
                FlightResponse flight = flightController.showInfo(flightId);
                printFlightHeader();
                displayFlight(flight);
                return;
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }

    private void showOnlineBoard() {
        Set<FlightResponse> flights = flightController.findAllWithinNext24Hours();
        System.out.println("Flights in next 24 hours: ");
        displayFlights(flights);
    }

    private void showCommandList() {
        System.out.println(
                "0 : exit the program\n" +
                        "1 : show online board\n" +
                        "2 : flight info\n" +
                        "3 : search and book a flight\n" +
                        "4 : cancel the booking\n" +
                        "5 : my flights"
        );
    }

    private long getValidId(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLong()) {
                return scanner.nextLong();
            }
            scanner.nextLine();
            System.out.println("Please enter a number!");
        }
    }

    private int getValidInt(String message) {
        System.out.print((message));
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            sc.nextLine();
            System.out.println(("Please enter a number!"));
        }
    }

    private String getName(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print((message));
        return sc.nextLine();
    }

    private void printFlightHeader() {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Id", "Date", "Time", "To", "Free seats");
    }

    private void printBookingHeader() {
        System.out.printf("%-15s%-15s%-30s%-30s%-15s\n",
                "ID", "Flight ID", "Created By", "passengers", "is active");
    }

    private void displayFlight(FlightResponse flight) {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n",
                flight.getFlightId(),
                flight.getDepartureDate().format(AppConstants.FLIGHT_DATE_FORMAT),
                flight.getDepartureTime().format(AppConstants.FLIGHT_DATE_FORMAT),
                flight.getDestinationPoint(),
                flight.getFreeSeats());
    }

    private void displayFlights(Set<FlightResponse> flights) {
        printFlightHeader();
        for (FlightResponse flight : flights) {
            displayFlight(flight);
        }
    }

    private void createFlights() {
        LocalDateTime dateTime = LocalDateTime.now();
        List<FlightCreateRequest> flights = new ArrayList<>(Arrays.asList(new FlightCreateRequest[]{
                new FlightCreateRequest(dateTime.plusHours(10), "Istanbul", 200),
                new FlightCreateRequest(dateTime.plusHours(15), "Baku", 150),
                new FlightCreateRequest(dateTime.plusHours(3), "Manchester", 300),
                new FlightCreateRequest(dateTime.plusHours(1), "Madeira", 100),
                new FlightCreateRequest(dateTime.plusHours(46), "New York", 150),
                new FlightCreateRequest(dateTime.plusHours(32), "London", 150),
                new FlightCreateRequest(dateTime.plusHours(12), "Moscow", 200),
                new FlightCreateRequest(dateTime.plusHours(55), "Paris", 100)
        }));
        flights.forEach(flightController::create);
    }
}

