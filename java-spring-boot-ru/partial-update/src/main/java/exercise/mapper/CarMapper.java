package exercise.mapper;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarDTO;
import exercise.dto.CarUpdateDTO;
import exercise.model.Car;
import org.mapstruct.*;

// BEGIN
@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CarMapper {
	public abstract CarDTO map(Car car);
	public abstract Car map(CarCreateDTO createCar);
}
// END
