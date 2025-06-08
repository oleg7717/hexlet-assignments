package exercise.mapper;

// BEGIN
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
	@Mapping(source = "name", target = "title")
	@Mapping(source = "cost", target = "price")
	@Mapping(source = "barcode", target = "vendorCode")
	public abstract ProductDTO map(Product product);

	@Mapping(source = "name", target = "title")
	@Mapping(source = "cost", target = "price")
	@Mapping(source = "barcode", target = "vendorCode")
	public abstract List<ProductDTO> map(List<Product> product);

	@Mapping(source = "title", target = "name")
	@Mapping(source = "price", target = "cost")
	@Mapping(source = "vendorCode", target = "barcode")
	public abstract Product map(ProductCreateDTO createDTO);

	@Mapping(source = "price", target = "cost")
	public abstract void update(ProductUpdateDTO updateDTO, @MappingTarget Product product);
}
// END
