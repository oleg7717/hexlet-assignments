package exercise.controller;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductRepository productRepository;

	// BEGIN
	@Autowired
	private ProductMapper productMapper;

	@GetMapping("")
	public List<ProductDTO> index() {
		var products = productRepository.findAll();
		return productMapper.map(products);
	}

	@GetMapping("/{id}")
	public ProductDTO show(@PathVariable Long id) {
		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
		return productMapper.map(product);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO create(@RequestBody ProductCreateDTO data) {
		var product = productMapper.map(data);
		productRepository.save(product);

		return productMapper.map(product);
	}

	@PutMapping("/{id}")
	public ProductDTO update(@PathVariable Long id, @RequestBody ProductUpdateDTO data) {
		var product = productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product with id " + id + " not found"));
		productMapper.update(data, product);
		productRepository.save(product);

		return productMapper.map(product);
	}
	// END
}
