package exercise.mapper;

import exercise.dto.GuestCreateDTO;
import exercise.dto.GuestDTO;
import exercise.model.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class GuestMapper {


	public abstract Guest map(GuestCreateDTO dto);

	public abstract GuestDTO map(Guest model);
}
