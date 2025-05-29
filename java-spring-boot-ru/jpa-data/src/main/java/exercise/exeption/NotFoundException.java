package exercise.exeption;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Long id) {
		super("Not found Person with id: " + id);
	}
}
