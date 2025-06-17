package exercise.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class BookService {
	// BEGIN
	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookMapper bookMapper;

	public List<BookDTO> getAllBooks() {
		return bookRepository.findAll().stream().map(bookMapper::map).toList();
	}

	public BookDTO showBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book with id "+ id + " not found"));
		return bookMapper.map(book);
	}


	public BookDTO createBook(BookCreateDTO bookDTO) {
		Book book = bookRepository.save(bookMapper.map(bookDTO));
		return bookMapper.map(book);
	}

	public BookDTO updateBookById(BookUpdateDTO bookDTO, Long id) throws JsonMappingException {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " no found"));
		bookMapper.update(book, bookDTO);
		bookRepository.save(book);
		return bookMapper.map(book);
	}

	public void deleteBookById(long id) {
		bookRepository.deleteById(id);
	}
	// END
}
