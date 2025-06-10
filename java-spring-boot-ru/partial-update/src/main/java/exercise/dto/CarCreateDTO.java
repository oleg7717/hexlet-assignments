package exercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarCreateDTO {
	private String model;
	private String manufacturer;
	private int enginePower;
}
