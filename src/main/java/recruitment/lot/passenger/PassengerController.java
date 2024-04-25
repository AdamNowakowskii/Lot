package recruitment.lot.passenger;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static recruitment.lot.common.Constants.API_PATH;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import recruitment.lot.passenger.dto.PassengerSaveDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_PATH + "/passengers")
public class PassengerController extends APIController {

    private final PassengerService passengerService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(
            HttpServletRequest request,
            @RequestBody @Valid PassengerSaveDTO dto) {
        return createWithPath(passengerService.save(dto), request);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(
            @PathVariable @Valid @ID Long id,
            @RequestBody @Valid PassengerSaveDTO dto) {
        passengerService.update(id, dto);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Valid @ID Long id) {
        passengerService.delete(id);
    }

}
