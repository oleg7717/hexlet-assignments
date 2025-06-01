package exercise.handler;

import exercise.exception.ResourceAlreadyExistsException;
import exercise.exception.ResourceMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import exercise.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<Responce> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
		Responce responce = new Responce(ex.getMessage());
		return new ResponseEntity<>(responce, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ResourceMismatchException.class)
	public ResponseEntity<Responce> handleResourceAlreadyExistsException(ResourceMismatchException ex) {
		Responce responce = new Responce(ex.getMessage());
		return new ResponseEntity<>(responce, HttpStatus.CONFLICT);
	}
}
