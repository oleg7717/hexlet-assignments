package exercise.controller;

import exercise.exeption.NotFoundException;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Person;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/{id}")
	public Person show(@PathVariable long id) throws NotFoundException {
		return personRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// BEGIN
	@GetMapping
	public List<Person> showAll() {
		return personRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person createPerson(@RequestBody Person data) {
		personRepository.save(data);
		return data;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable long id) {
		personRepository.deleteById(id);
	}
	// END
}
