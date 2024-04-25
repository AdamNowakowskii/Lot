package recruitment.lot.passenger;

import static recruitment.lot.common.exception.NotFoundException.forResourceNotFound;

import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import recruitment.lot.passenger.dto.PassengerSaveDTO;
import recruitment.lot.passenger.dto.PassengerSearchDTO;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public Set<PassengerSearchDTO> findByFlight(Long id) {
        return passengerRepository.findByFlight(id);
    }

    @Transactional
    public Long save(PassengerSaveDTO dto) {
        return passengerRepository.save(dto.toEntity()).id;
    }

    @Transactional
    public void update(Long id, PassengerSaveDTO dto) {
        Passenger entity = passengerRepository
                .findById(id)
                .orElseThrow(() -> forResourceNotFound("Passenger", id));
        dto.setEntity(entity);
    }

    @Transactional
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

}
