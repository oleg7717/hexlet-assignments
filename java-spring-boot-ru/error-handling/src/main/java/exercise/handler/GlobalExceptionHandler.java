package exercise.handler;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// BEGIN
@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> handleException(ResourceNotFoundException notFound) {
		Response response = new Response(notFound.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
// END
