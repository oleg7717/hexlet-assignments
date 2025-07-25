package exercise.util;

import exercise.model.Task;
import exercise.model.User;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelGenerator {
	private Model<Task> taskModel;
	private Model<User> userModel;

	public Model<User> getUserModel() {
		return userModel;
	}

	public Model<Task> getTaskModel() {
		return taskModel;
	}

	@Autowired
	private Faker faker;

	@PostConstruct
	private void init() {
		userModel = Instancio.of(User.class)
				.ignore(Select.field(User::getId))
				.ignore(Select.field(User::getTasks))
				.supply(Select.field(User::getName), () -> faker.name().fullName())
				.supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
				.toModel();


		taskModel = Instancio.of(Task.class)
				.ignore(Select.field(Task::getId))
				.supply(Select.field(Task::getTitle), () -> faker.lorem().word())
				.supply(Select.field(Task::getDescription), () -> faker.gameOfThrones().quote())
				.toModel();
	}
}
