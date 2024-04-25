package recruitment.lot.passenger;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import recruitment.lot.passenger.dto.PassengerSearchDTO;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query(
            "SELECT new recruitment.lot.passenger.dto.PassengerSearchDTO(" +
                    "p.firstName, p.lastName, p.phoneNumber) " +
                    "FROM Flight f JOIN f.passengers p WHERE f.id = :id")
    Set<PassengerSearchDTO> findByFlight(@Param("id") Long id);

}
