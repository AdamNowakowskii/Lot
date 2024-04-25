package recruitment.lot.flight.dto;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import recruitment.lot.common.constraint.IDs;
import recruitment.lot.flight.Flight;
import recruitment.lot.passenger.Passenger;

@Builder
public record FlightDTO(
        @NotBlank @Size(max = 50) String flightNumber,
        @NotNull @Size(min = 1) LinkedHashSet<String> routes,
        @NotNull LocalDateTime departureDate,
        @NotNull @PositiveOrZero Integer availableSeats,
        @NotNull @IDs Set<Long> passengersIDs) {

    public Flight toEntity(Set<Passenger> passengers) {
        return Flight.builder()
                .flightNumber(flightNumber.toUpperCase())
                .route(String.join(",", routes))
                .departureDate(departureDate)
                .availableSeats(availableSeats)
                .passengers(passengers)
                .build();
    }

    public static FlightDTO fromEntity(Flight entity) {
        return FlightDTO.builder()
                .flightNumber(entity.flightNumber)
                .routes(stream(entity.route.split(",")).collect(toCollection(LinkedHashSet::new)))
                .departureDate(entity.departureDate)
                .availableSeats(entity.availableSeats)
                .passengersIDs(entity.passengers.stream().map(p -> p.id).collect(Collectors.toSet()))
                .build();
    }

    public void setEntity(Flight flight, Set<Passenger> passengers) {
        flight.flightNumber = flightNumber;
        flight.route = String.join(",", routes);
        flight.departureDate = departureDate;
        flight.availableSeats = availableSeats;
        flight.passengers = passengers;
    }

}
