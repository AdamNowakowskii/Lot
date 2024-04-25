package recruitment.lot.flight.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record FlightCriteriaDTO(
        @Size(max = 100) String startingPoint,
        @Size(max = 100) String endingPoint,
        LocalDateTime departureFrom,
        LocalDateTime departureTo,
        @PositiveOrZero Integer minAvailableSeats) {}
