package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Task;
import exercise.repository.TaskRepository;
import net.datafaker.Faker;
import org.assertj.core.api.Assert;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Faker faker;

	@Autowired
	private ObjectMapper om;

	@Autowired
	private TaskRepository taskRepository;


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
		var result = mockMvc.perform(get("/tasks"))
				.andExpect(status().isOk())
				.andReturn();

		var body = result.getResponse().getContentAsString();
		assertThatJson(body).isArray();
	}

	// BEGIN
	@Test
	public void testShow() throws Exception {
		var task = createTaskResord();
		var result = mockMvc.perform(get("/tasks/{id}", task.getId()))
				.andExpect(status().isOk())
				.andReturn();

		var body = result.getResponse().getContentAsString();
		assertThatJson(body).and(
				a -> a.node("title").isEqualTo(task.getTitle()),
				a -> a.node("description").isEqualTo(task.getDescription())
		);
	}

	@Test
	public void testCreate() throws Exception {
		var data = new HashMap();
		data.put("title", "title1");
		data.put("description", "desc");
		var request = post("/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(data));
		var result = mockMvc.perform(request)
				.andExpect(status().isCreated());

		Task task = taskRepository.findByTitle((String) data.get("title")).get();
		assertThat(task).isNotNull();
		assertThat(task.getTitle()).isEqualTo(data.get("title"));
		assertThat(task.getDescription()).isEqualTo(data.get("description"));
	}

	@Test
	public void testUpdate() throws Exception {
		var task = createTaskResord();
		var data = new HashMap();
		data.put("description", "desc");
		var request = put("/tasks/{id}", task.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(data));
		var result = mockMvc.perform(request)
				.andExpect(status().isOk());

		Task foundTask = taskRepository.findById(task.getId()).get();
		assertThat(foundTask.getDescription()).isEqualTo(data.get("description"));
	}

	@Test
	public void testDelete() throws Exception {
		var task = createTaskResord();
		mockMvc.perform(delete("/tasks/{id}", task.getId()))
				.andExpect(status().isOk());

		assertThat(taskRepository.findById(task.getId())).isEmpty();
	}

	public Task createTaskResord() {
		var task = Instancio.of(Task.class)
				.ignore(Select.field(Task::getId))
				.supply(Select.field(Task::getTitle), () -> faker.cat().name())
				.supply(Select.field(Task::getDescription), () -> faker.lorem().word())
				.create();

		return taskRepository.save(task);
	}
	// END
}
