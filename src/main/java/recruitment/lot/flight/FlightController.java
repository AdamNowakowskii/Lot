package recruitment.lot.flight;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static recruitment.lot.common.Constants.API_PATH;

import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import recruitment.lot.common.APIController;
import recruitment.lot.common.constraint.ID;
import recruitment.lot.common.constraint.IDs;
import recruitment.lot.flight.dto.FlightCriteriaDTO;
import recruitment.lot.flight.dto.FlightDTO;
import recruitment.lot.flight.dto.FlightSearchDTO;
import recruitment.lot.passenger.PassengerService;
import recruitment.lot.passenger.dto.PassengerSearchDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_PATH + "/flights")
public class FlightController extends APIController {

    private final FlightService flightService;
    private final PassengerService passengerService;

    @ResponseStatus(OK)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public FlightSearchDTO find(@PathVariable @Valid @ID Long id) {
        return flightService.find(id);
    }

    @ResponseStatus(OK)
    @PostMapping(value = "/search",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public Set<FlightDTO> findByCriteria(
            @RequestBody @Valid FlightCriteriaDTO criteria,
            Pageable pagingParameters) {
        return flightService.findByCriteria(criteria, pagingParameters);
    }

    @ResponseStatus(OK)
    @GetMapping(value = "/{id}/passengers", produces = APPLICATION_JSON_VALUE)
    public Set<PassengerSearchDTO> findPassengers(@PathVariable @Valid @ID Long id) {
        return passengerService.findByFlight(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(
            HttpServletRequest request,
            @RequestBody @Valid FlightDTO dto) {
        return createWithPath(flightService.create(dto), request);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(
            @PathVariable @Valid @ID Long id,
            @RequestBody @Valid FlightDTO dto) {
        flightService.update(id, dto);
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping(value = "/{id}/passengers")
    public void updatePassengers(
            @PathVariable @Valid @ID Long id,
            @RequestBody @Valid @IDs Set<Long> passengerIDs) {
        flightService.modifyPassengers(id, passengerIDs);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Valid @ID Long id) {
        flightService.delete(id);
    }

}
