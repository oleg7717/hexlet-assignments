package exercise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	@NotBlank
	@EqualsAndHashCode.Include
	@JsonProperty("title")
	private String title;

	@NotNull
	@JsonProperty("price")
	private int price;

	@CreatedDate
	private LocalDate createdAt;

	@LastModifiedDate
	private LocalDate updatedAt;

	@NotNull
	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category.id")
	@JsonProperty("categoryId")
	private Category category;
}
