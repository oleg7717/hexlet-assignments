package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.*;

// BEGIN
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"title", "price"})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String title;
	int price;
}
// END
