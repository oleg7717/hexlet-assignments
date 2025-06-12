package exercise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import exercise.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Optional;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUpdateDTO {
	private Category category;
	@JsonProperty("categoryId")
	private void unpackNested(Integer categoryId) {
		this.category = new Category();
		category.setId(categoryId);
	}
	private Optional<Long> categoryId;

	@JsonProperty("title")
	private Optional<String> title;

	@JsonProperty("price")
	private Optional<Integer> price;
}
