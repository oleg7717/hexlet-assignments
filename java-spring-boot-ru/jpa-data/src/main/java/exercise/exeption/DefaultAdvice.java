package exercise.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class DefaultAdvice {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Response> handleException(NotFoundException notFound) {
		Response response = new Response(notFound.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Response> handleFormatException(NoResourceFoundException formatEx) {
		Response response = new Response("Not found Person.");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
