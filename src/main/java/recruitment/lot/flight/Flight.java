package recruitment.lot.flight;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import recruitment.lot.passenger.Passenger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long id;

    @NotBlank
    @Size(max = 50)
    public String flightNumber;

    @NotBlank
    @Size(max = 500)
    public String route;

    @NotNull
    public LocalDateTime departureDate;

    @NotNull
    @PositiveOrZero
    public Integer availableSeats;

    @ManyToMany(cascade = REMOVE)
    @JoinTable(
            name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    public Set<Passenger> passengers = new HashSet<>();

}
