package exercise.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.handler.ReferenceException;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CategoryRepository categoryRepository;

/*	@Autowired
	ObjectMapper objectMapper;*/


	// BEGIN
	@GetMapping(path = "")
	public List<ProductDTO> index() {
		return productRepository.findAll().stream()
				.map(productMapper::map)
				.collect(Collectors.toList());
	}

	@GetMapping(path = "/{id}")
	public ProductDTO show(@PathVariable Long id) {
		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
		return productMapper.map(product);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO create(@Valid @RequestBody ProductCreateDTO productData) {
		var categoryId = productData.getCategoryId();
		categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ReferenceException("Category with id " + categoryId + " not found"));
		var product = productMapper.map(productData);
		productRepository.save(product);

		return productMapper.map(product);
	}

	@PutMapping(path = "/{id}")
	public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO productData) {
		var categoryId = productData.getCategoryId();
		if(categoryId != null && categoryId.isPresent()) {
			categoryRepository.findById(categoryId.get())
					.orElseThrow(() -> new ReferenceException("Category with id " + categoryId + " not found"));
		}

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
		productMapper.update(product, productData);
//		objectMapper.updateValue(product, productData);
		productRepository.save(product);

		return productMapper.map(product);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productRepository.deleteById(id);
	}
	// END
}
