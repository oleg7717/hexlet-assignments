package exercise.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

// BEGIN
@Mapper(
		uses = {ReferenceMapper.class},
		componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ProductMapper {
	@Mapping(source = "category.id", target = "categoryId")
	@Mapping(source = "category.name", target = "categoryName")
	public abstract ProductDTO map(Product product);

	@Mapping(source = "categoryId", target = "category")
	public abstract Product map(ProductCreateDTO productDTO);

//	@Mapping(source = "categoryId", target = "category.id")
//	public abstract void update(@MappingTarget Product product, ProductUpdateDTO productDTO) throws JsonProcessingException;
}
// END
