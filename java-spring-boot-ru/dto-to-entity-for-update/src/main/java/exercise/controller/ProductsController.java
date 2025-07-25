package exercise.controller;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
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

	@GetMapping(path = "")
	public List<ProductDTO> index() {
		var products = productRepository.findAll();
		return products.stream()
				.map(this::toDTO)
				.toList();
	}

	@GetMapping(path = "/{id}")
	public ProductDTO show(@PathVariable long id) {

		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
		return toDTO(product);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO create(@RequestBody ProductCreateDTO productData) {
		var product = toEntity(productData);
		productRepository.save(product);
		return toDTO(product);
	}

	// BEGIN
	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDTO data) {
		var product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
		toEntity(data, product);
		productRepository.save(product);

		return toDTO(product);
	}

	private void toEntity(ProductUpdateDTO productDto, Product product) {
		product.setTitle(productDto.getTitle());
		product.setPrice(productDto.getPrice());
	}
	// END

	private Product toEntity(ProductCreateDTO productDto) {
		var product = new Product();
		product.setTitle(productDto.getTitle());
		product.setPrice(productDto.getPrice());
		product.setVendorCode(productDto.getVendorCode());
		return product;
	}

	private ProductDTO toDTO(Product product) {
		var dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setTitle(product.getTitle());
		dto.setPrice(product.getPrice());
		dto.setVendorCode(product.getVendorCode());
		dto.setCreatedAt(product.getCreatedAt());
		dto.setUpdatedAt(product.getUpdatedAt());
		return dto;
	}
}
