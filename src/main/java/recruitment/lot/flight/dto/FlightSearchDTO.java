package recruitment.lot.flight.dto;

import java.time.LocalDateTime;
import java.util.Set;

public interface FlightSearchDTO {

    String getFlightNumber();

    String getRoute();

    LocalDateTime getDepartureDate();

    Integer getAvailableSeats();

    Set<Passenger> getPassengers();

    interface Passenger {
        String getId();
    }

}
