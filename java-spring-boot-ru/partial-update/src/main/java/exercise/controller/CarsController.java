package exercise.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.CarCreateDTO;
import exercise.dto.CarDTO;
import exercise.dto.CarUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.CarMapper;
import exercise.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarMapper carMapper;

	@Autowired
	private ObjectMapper objectMapper;


	@GetMapping(path = "")
	public List<CarDTO> index() {
		var cars = carRepository.findAll();
		return cars.stream()
				.map(p -> carMapper.map(p))
				.toList();
	}

	@GetMapping(path = "/{id}")
	public CarDTO show(@PathVariable long id) {

		var car = carRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
		return carMapper.map(car);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public CarDTO create(@RequestBody CarCreateDTO carData) {
		var car = carMapper.map(carData);
		carRepository.save(car);
		return carMapper.map(car);
	}

	@PutMapping(path = "/{id}")
	public CarDTO update(@PathVariable long id, @RequestBody CarUpdateDTO carData) throws JsonMappingException {

		var car = carRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));

		objectMapper.updateValue(car, carData);
		carRepository.save(car);

		return carMapper.map(car);
	}
}
