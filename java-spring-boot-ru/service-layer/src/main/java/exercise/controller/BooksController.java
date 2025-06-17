package exercise.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.repository.BookRepository;
import exercise.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
	@Autowired
	private BookService bookService;

	// BEGIN
	@GetMapping(path = "")
	public List<BookDTO> index() {
		return bookService.getAllBooks();
	}

	@GetMapping(path = "/{id}")
	public BookDTO show(@PathVariable Long id) {
		return bookService.showBookById(id);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO create(@RequestBody @Valid BookCreateDTO bookDTO) {
		return bookService.createBook(bookDTO);
	}

	@PutMapping(path = "/{id}")
	public BookDTO update(@RequestBody @Valid BookUpdateDTO bookDTO, @PathVariable Long id) throws JsonMappingException {
		return bookService.updateBookById(bookDTO, id);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		bookService.deleteBookById(id);
	}
	// END
}
