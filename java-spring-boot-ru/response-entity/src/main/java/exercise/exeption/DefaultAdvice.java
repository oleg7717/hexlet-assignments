package exercise.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Response> handleException(NotFoundException notFound) {
		Response response = new Response(notFound.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
