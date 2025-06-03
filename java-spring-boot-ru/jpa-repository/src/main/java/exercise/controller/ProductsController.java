package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository productRepository;

	// BEGIN
	@GetMapping(path = "")
	public List<Product> filterByPrice(@RequestParam(defaultValue = 0 + "", required = false) Integer min,
	                                   @RequestParam(defaultValue = Integer.MAX_VALUE + "", required = false) Integer max) {
		Sort sort = Sort.by(Sort.Order.asc("price"));
		return productRepository.findByPriceBetween(min, max, sort);
	}
	// END

	@GetMapping(path = "/{id}")
	public Product show(@PathVariable long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
	}
}
