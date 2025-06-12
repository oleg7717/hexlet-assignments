package exercise.controller;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.CategoryMapper;
import exercise.model.Category;
import exercise.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@GetMapping(path = "")
	public List<CategoryDTO> index() {
		var categories = categoryRepository.findAll();
		return categories.stream()
				.map(categoryMapper::map)
				.toList();
	}

	@GetMapping(path = "/{id}")
	public CategoryDTO show(@PathVariable long id) {

		var category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
		return categoryMapper.map(category);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryDTO create(@Valid @RequestBody CategoryCreateDTO categoryData) {
		var category = categoryMapper.map(categoryData);
		categoryRepository.save(category);

		return categoryMapper.map(category);
	}

	@PostMapping(path = "/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<CategoryDTO> createList(@Valid @RequestBody List<CategoryCreateDTO> categoryData) {
		List<Category> categoryDTOList = categoryData.stream()
				.map(categoryMapper::map).toList();
		return categoryDTOList.stream()
				.peek(c -> categoryRepository.save(c))
				.map(categoryMapper::map).toList();
	}
}
