package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class GuestDTO {
	private long id;

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

	@Future
	private LocalDate cardValidUntil;

	private LocalDate createdAt;
}
