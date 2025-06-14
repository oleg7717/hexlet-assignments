package exercise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "assignees")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class Task {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@CreatedDate
	private LocalDate createdAt;

	@LastModifiedDate
	private LocalDate updatedAt;

	// BEGIN
	@NotNull
	@ManyToOne(optional = false)
	private User assignee;
	// END
}
