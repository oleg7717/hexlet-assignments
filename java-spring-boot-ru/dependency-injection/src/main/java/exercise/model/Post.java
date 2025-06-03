package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	private String title;

	private String body;

	@CreatedDate
	private LocalDate createdAt;
}
