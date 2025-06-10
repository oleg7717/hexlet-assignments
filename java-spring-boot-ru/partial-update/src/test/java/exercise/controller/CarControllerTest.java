package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.CarUpdateDTO;
import exercise.model.Car;
import exercise.repository.CarRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Faker faker;

	@Autowired
	private ObjectMapper om;

	@Autowired
	private CarRepository carRepository;

	private Car testCar;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
				.build();
		testCar = Instancio.of(Car.class)
				.ignore(Select.field(Car::getId))
				.supply(Select.field(Car::getModel), () -> faker.brand().car())
				.supply(Select.field(Car::getManufacturer), () -> faker.brand().car())
				.supply(Select.field(Car::getEnginePower), () -> faker.number().numberBetween(1, 100))
				.create();
	}

	@Test
	public void testWelcomePage() throws Exception {
		var result = mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andReturn();

		var body = result.getResponse().getContentAsString();
		assertThat(body).contains("Welcome to Spring!");
	}

	@Test
	public void testIndex() throws Exception {
		carRepository.save(testCar);
		var result = mockMvc.perform(get("/cars"))
				.andExpect(status().isOk())
				.andReturn();

		var body = result.getResponse().getContentAsString();
		assertThatJson(body).isArray();
	}

	@Test
	public void testShow() throws Exception {

		carRepository.save(testCar);

		var request = get("/cars/{id}", testCar.getId());
		var result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
		var body = result.getResponse().getContentAsString();

		assertThatJson(body).and(
				v -> v.node("model").isEqualTo(testCar.getModel()),
				v -> v.node("manufacturer").isEqualTo(testCar.getManufacturer())
		);
	}

	@Test
	public void testUpdate() throws Exception {

		carRepository.save(testCar);

		var data = new CarUpdateDTO();
		data.setManufacturer(Optional.of("new manufacturer"));

		var request = put("/cars/{id}", testCar.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(data));

		mockMvc.perform(request)
				.andExpect(status().isOk());

		var car = carRepository.findById(testCar.getId()).get();

		assertThatJson(car).and(
				v -> v.node("model").isEqualTo(testCar.getModel()),
				v -> v.node("manufacturer").isEqualTo(data.getManufacturer()),
				v -> v.node("enginePower").isEqualTo(testCar.getEnginePower())
		);
	}
}
