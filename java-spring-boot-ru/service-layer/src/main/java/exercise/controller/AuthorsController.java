package exercise.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import exercise.dto.*;
import exercise.dto.AuthorDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

	@Autowired
	private AuthorService authorService;

	// BEGIN
	@GetMapping(path = "")
	public List<AuthorDTO> index() {
		return authorService.getAllAuthors();
	}

	@GetMapping(path = "/{id}")
	public AuthorDTO show(@PathVariable Long id) {
		return authorService.showAuthorById(id);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public AuthorDTO create(@RequestBody @Valid AuthorCreateDTO AuthorDTO) {
		return authorService.createAuthor(AuthorDTO);
	}

	@PutMapping(path = "/{id}")
	public AuthorDTO update(@RequestBody @Valid AuthorUpdateDTO AuthorDTO, @PathVariable Long id)
			throws JsonMappingException {
		return authorService.updateAuthorById(AuthorDTO, id);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		authorService.deleteAuthorById(id);
	}
	// END
}
