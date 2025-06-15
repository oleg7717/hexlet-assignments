package exercise.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParamsDTO {
	private String titleCont;
	private Long categoryId;
	private Integer priceLt;
	private Integer priceGt;
	private Double ratingGt;
}
