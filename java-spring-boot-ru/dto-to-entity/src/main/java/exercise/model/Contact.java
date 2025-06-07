package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "contacts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Contact {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	private String firstName;

	private String lastName;

	private String phone;

	@LastModifiedDate
	private LocalDate updatedAt;

	@CreatedDate
	private LocalDate createdAt;
}
