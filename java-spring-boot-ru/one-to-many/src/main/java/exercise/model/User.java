package exercise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	public void setId(long id) {
		this.id = id;
	}

	public void setName(@NotBlank String name) {
		this.name = name;
	}

	public void setEmail(@Email String email) {
		this.email = email;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public long getId() {
		return id;
	}

	public @NotBlank String getName() {
		return name;
	}

	public @Email String getEmail() {
		return email;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	@NotBlank
	private String name;

	@Email
	@Column(unique = true)
	private String email;

	@CreatedDate
	private LocalDate createdAt;

	// BEGIN
	@OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks = new ArrayList<>();

	public void addTask(Task task) {
		tasks.add(task);
		task.setAssignee(this);
	}

	public void removeTask(Task task) {
		tasks.remove(task);
		task.setAssignee(null);
	}
	// END
}
