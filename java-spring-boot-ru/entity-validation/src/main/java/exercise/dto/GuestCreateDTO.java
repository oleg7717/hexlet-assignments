package exercise.dto;

// BEGIN
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class GuestCreateDTO {
	@NotBlank
	private String name;

	@Email
	private String email;

	@Pattern(regexp = "^\\+[0-9]+$")
	@Size(min = 12, max = 14)
	private String phoneNumber;

	@Size(min = 4)
	@Digits(integer = 4, fraction = 0)
	private String clubCard;

	@FutureOrPresent
	private LocalDate cardValidUntil;
}
// END
