package exercise.mapper;

import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

// BEGIN
@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING
)
public class ReferenceMapper {
	@Autowired
	EntityManager entityManager;

	public <T extends BaseEntity> T toEntity(@TargetType Class<T> entityClass, Long id) {
		return id != null ? entityManager.find(entityClass, id) : null;
	}
}
// END
