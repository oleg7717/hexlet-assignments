package exercise.controller;

import exercise.exception.ResourceAlreadyExistsException;
import exercise.exception.ResourceMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/tasks")
public class TasksController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping(path = "")
	public List<Task> index() {
		return taskRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Task show(@PathVariable long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
	}

	// BEGIN
	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@RequestBody Task data) {
		if (taskRepository.findAll().parallelStream().anyMatch(task -> task.equals(data))) {
			throw new ResourceAlreadyExistsException("Task with title: " + data.getTitle() + " already exist");
		}

		return taskRepository.save(data);
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task data) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " not found"));

/*		if (taskRepository.findAll().parallelStream().anyMatch(t -> t.equals(data) && !t.getId().equals(id))) {
			throw new ResourceMismatchException("You can not update task with title from other task");
		}*/

		task.setTitle(data.getTitle());
		task.setDescription(data.getDescription());

		return taskRepository.save(task);
	}
	// END

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable long id) {
		taskRepository.deleteById(id);
	}
}
