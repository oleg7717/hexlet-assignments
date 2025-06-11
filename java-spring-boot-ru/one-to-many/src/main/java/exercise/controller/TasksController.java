package exercise.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.TaskMapper;
import exercise.model.User;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TaskMapper taskMapper;

	@Autowired
	ObjectMapper objectMapper;
	// BEGIN
	@GetMapping(path = "")
	public List<TaskDTO> index() {
		var tasks = taskRepository.findAll();
		return tasks.stream()
				.map(p -> taskMapper.map(p))
				.toList();
	}

	@GetMapping(path = "/{id}")
	public TaskDTO show(@PathVariable long id) {
		var task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
		return taskMapper.map(task);
	}

	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public TaskDTO create(@Valid @RequestBody TaskCreateDTO taskData) {
		var userId = taskData.getAssigneeId();
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
		var task = taskMapper.map(taskData);
		user.addTask(task);
		taskRepository.save(task);

		return taskMapper.map(task);
	}

	@PutMapping(path = "/{id}")
	public TaskDTO update(@PathVariable long id,
	                      @Valid @RequestBody TaskUpdateDTO taskData) throws JsonMappingException {
		var task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

		var userId = taskData.getAssigneeId();
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
		user.addTask(task);

		taskMapper.update(taskData, task);
		taskRepository.save(task);

		return taskMapper.map(task);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		var task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
		taskRepository.deleteById(id);
	}
	// END
}
