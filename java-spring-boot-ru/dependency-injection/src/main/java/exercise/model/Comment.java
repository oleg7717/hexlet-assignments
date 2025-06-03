package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Comment {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	private long postId;
	private String body;

	@CreatedDate
	private LocalDate createdAt;
}
