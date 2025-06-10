package exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cars")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Car {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@JsonProperty("model")
	@Column(name = "model",
			nullable = true)
	private String model;

	@JsonProperty("manufacturer")
	@Column(name = "manufacturer",
			nullable = true)
	private String manufacturer;

	@JsonProperty("enginePower")
	@Column(name = "enginePower",
			nullable = true)
	private int enginePower;

	@LastModifiedDate
	private LocalDate updatedAt;

	@CreatedDate
	private LocalDate createdAt;
}
