package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

// BEGIN
@Entity
@Table(name = "person")
@Setter
@Getter
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String firstName;

	String lastName;
}
// END
