package recruitment.lot.passenger;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import recruitment.lot.common.constraint.PhoneNumber;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Long id;

    @NotBlank
    @Size(max = 100)
    public String firstName;

    @NotBlank
    @Size(max = 100)
    public String lastName;

    @NotNull
    @PhoneNumber
    public String phoneNumber;

}
