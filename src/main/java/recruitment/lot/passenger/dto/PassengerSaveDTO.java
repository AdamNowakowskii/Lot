package recruitment.lot.passenger.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import recruitment.lot.common.constraint.PhoneNumber;
import recruitment.lot.passenger.Passenger;

public record PassengerSaveDTO(
        @NotBlank @Size(max = 100) String firstName,
        @NotBlank @Size(max = 100) String lastName,
        @NotNull @PhoneNumber String phoneNumber) {

    public Passenger toEntity() {
        return Passenger.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
    }

    public void setEntity(Passenger entity) {
        entity.firstName = firstName;
        entity.lastName = lastName;
        entity.phoneNumber = phoneNumber;
    }

}
