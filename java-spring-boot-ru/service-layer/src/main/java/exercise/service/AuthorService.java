package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
	// BEGIN
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	AuthorMapper authorMapper;

	public List<AuthorDTO> getAllAuthors() {
		return authorRepository.findAll().stream().map(authorMapper::map).toList();
	}

	public AuthorDTO showAuthorById(Long id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
		return authorMapper.map(author);
	}

	public AuthorDTO createAuthor(AuthorCreateDTO authorDTO) {
		Author author = authorRepository.save(authorMapper.map(authorDTO));
		return authorMapper.map(author);
	}

	public AuthorDTO updateAuthorById(AuthorUpdateDTO authorDTO, Long id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
		authorMapper.update(author, authorDTO);
		authorRepository.save(author);

		return authorMapper.map(author);
	}

	public void deleteAuthorById(Long id) {
		authorRepository.deleteById(id);

	}
	// END
}
