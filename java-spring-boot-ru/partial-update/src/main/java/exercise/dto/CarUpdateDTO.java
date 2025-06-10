package exercise.dto;

// BEGIN

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarUpdateDTO {
	@JsonProperty("model")
	private Optional<String> model;

	@JsonProperty("manufacturer")
	private Optional<String> manufacturer;

	@JsonProperty("enginePower")
	private Optional<Integer> enginePower;
}
// END
