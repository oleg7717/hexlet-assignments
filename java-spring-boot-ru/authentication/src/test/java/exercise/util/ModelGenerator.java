package exercise.util;

import exercise.model.Article;
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
	public Model<Article> getArticleModel() {
		return articleModel;
	}

	public void setArticleModel(Model<Article> articleModel) {
		this.articleModel = articleModel;
	}

	public Model<User> getUserModel() {
		return userModel;
	}

	public void setUserModel(Model<User> userModel) {
		this.userModel = userModel;
	}

	private Model<Article> articleModel;
	private Model<User> userModel;

	@Autowired
	private Faker faker;

	@PostConstruct
	private void init() {
		userModel = Instancio.of(User.class)
				.ignore(Select.field(User::getId))
				.ignore(Select.field(User::getArticles))
				.supply(Select.field(User::getName), () -> faker.name().fullName())
				.supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
				.supply(Select.field(User::getPasswordDigest), () -> faker.internet().password(3, 100))
				.toModel();

		articleModel = Instancio.of(Article.class)
				.ignore(Select.field(Article::getId))
				.supply(Select.field(Article::getTitle), () -> faker.lorem().word())
				.supply(Select.field(Article::getSlug), () -> faker.lorem().word())
				.supply(Select.field(Article::getContent), () -> faker.gameOfThrones().quote())
				.toModel();
	}
}
