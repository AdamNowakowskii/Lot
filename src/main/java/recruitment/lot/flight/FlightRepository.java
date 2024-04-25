package recruitment.lot.flight;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long>, PagingAndSortingRepository<Flight, Long> {

    <E> Optional<E> findById(Long id, Class<E> c);

    @Query("SELECT f FROM Flight f " +
            "WHERE (:departureFrom IS NULL OR f.departureDate >= :departureFrom) " +
            "AND (:departureTo IS NULL OR f.departureDate <= :departureTo) " +
            "AND (:minSeats IS NULL OR f.availableSeats >= :minSeats)" +
            "AND (:startPoint IS NULL OR f.route LIKE :startPoint% )" +
            "AND (:endingPoint IS NULL OR f.route LIKE %:endingPoint)")
    Page<Flight> findByCriteria(
            @Param("departureFrom") LocalDateTime departureFrom,
            @Param("departureTo") LocalDateTime departureTo,
            @Param("minSeats") Integer minSeats,
            @Param("startPoint") String startPoint,
            @Param("endingPoint") String endingPoint,
            Pageable pagingParameters);

    boolean existsByFlightNumberIgnoreCase(String flightNumber);

}
