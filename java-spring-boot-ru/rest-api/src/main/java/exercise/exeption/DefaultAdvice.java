package exercise.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Response> handleNotFoundException(NotFoundException notFound) {
		Response response = new Response(notFound.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Response> handleFormatException(NumberFormatException formatEx) {
		Response response = new Response("User id from URL error while parsing.");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
