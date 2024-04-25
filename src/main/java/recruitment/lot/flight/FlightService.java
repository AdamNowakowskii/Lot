package recruitment.lot.flight;

import static recruitment.lot.common.exception.ConflictException.forExistingResource;
import static recruitment.lot.common.exception.NotFoundException.forResourceNotFound;
import static recruitment.lot.common.exception.UnprocessableEntityException.forInvalidSubset;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import recruitment.lot.flight.dto.FlightCriteriaDTO;
import recruitment.lot.flight.dto.FlightDTO;
import recruitment.lot.flight.dto.FlightSearchDTO;
import recruitment.lot.passenger.Passenger;
import recruitment.lot.passenger.PassengerRepository;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    public FlightSearchDTO find(Long id) {
        return findOrThrow(id, FlightSearchDTO.class);
    }

    public Set<FlightDTO> findByCriteria(
            FlightCriteriaDTO criteria, Pageable pagingParameters) {
        Page<Flight> result = flightRepository.findByCriteria(
                criteria.departureFrom(),
                criteria.departureTo(),
                criteria.minAvailableSeats(),
                criteria.startingPoint(),
                criteria.endingPoint(),
                pagingParameters);
        return result.stream()
                .map(FlightDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Transactional
    public Long create(FlightDTO dto) {
        throwUponExisting(dto.flightNumber());
        return flightRepository.save(dto.toEntity(findPassengersOrThrow(dto.passengersIDs()))).id;
    }

    @Transactional
    public void update(Long id, FlightDTO dto) {
        throwUponExisting(dto.flightNumber());
        Flight flight = findOrThrow(id, Flight.class);
        dto.setEntity(flight, findPassengersOrThrow(dto.passengersIDs()));
    }

    @Transactional
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Transactional
    public void modifyPassengers(Long id, Set<Long> passengersIDs) {
        Flight flight = flightRepository.findById(id).orElseThrow();
        int currentCount = flight.passengers.size();
        flight.passengers = findPassengersOrThrow(passengersIDs);
        flight.availableSeats += currentCount - flight.passengers.size();
    }

    private <E> E findOrThrow(Long id, Class<E> c) {
        return flightRepository
                .findById(id, c)
                .orElseThrow(() -> forResourceNotFound("Flight", id));
    }

    private Set<Passenger> findPassengersOrThrow(Set<Long> ids) {
        Set<Passenger> passengers = new HashSet<>(passengerRepository.findAllById(ids));

        if (passengers.size() != ids.size()) {
            ids.removeAll(passengers.stream().map(p -> p.id).collect(Collectors.toSet()));
            throw forInvalidSubset("Passengers", ids);
        }

        return passengers;
    }

    private void throwUponExisting(String flightNumber) {
        if (flightRepository.existsByFlightNumberIgnoreCase(flightNumber))
            throw forExistingResource("Flight", flightNumber);
    }

}
