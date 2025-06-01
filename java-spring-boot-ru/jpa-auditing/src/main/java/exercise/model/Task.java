package exercise.model;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// BEGIN
@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "title")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	Long id;
	String title;
	String description;
	@CreatedDate
	LocalDateTime createdAt;
	@LastModifiedDate
	LocalDateTime updatedAt;
}
// END
