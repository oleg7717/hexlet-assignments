package exercise.mapper;

import exercise.dto.*;
import exercise.model.Book;
import org.mapstruct.*;

@Mapper(
		uses = {JsonNullableMapper.class, ReferenceMapper.class},
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class BookMapper {

	// BEGIN
	@Mapping(source = "author.id", target = "authorId")
	@Mapping(source = "author.firstName", target = "authorFirstName")
	@Mapping(source = "author.lastName", target = "authorLastName")
	public abstract BookDTO map (Book book);

	@Mapping(source = "authorId", target = "author")
	public abstract Book map (BookCreateDTO bookDTO);
	// END

	@Mapping(source = "authorId", target = "author")
	public abstract void update(@MappingTarget Book model, BookUpdateDTO dto);
}
