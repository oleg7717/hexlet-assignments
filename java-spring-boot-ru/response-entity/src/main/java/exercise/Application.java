package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;
import lombok.Setter;

@SpringBootApplication
@RestController
public class Application {
	// Хранилище добавленных постов
	@Setter
	private static List<Post> posts = Data.getPosts();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// BEGIN
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getPostsByLimitAndOffset(@RequestParam @Nullable Integer limit,
	                                           @RequestParam(defaultValue = "1") Integer pageNumber) {
		int lim = Optional.ofNullable(limit).orElse(posts.size());
//		return posts.stream().skip((long) lim * (pageNumber - 1)).limit(lim).toList();
		return ResponseEntity.ok()
				.header("X-Total-Count", String.valueOf(posts.size()))
				.body(posts.stream().skip((long) lim * (pageNumber - 1)).limit(lim).toList());
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<Optional<Post>> getPost(@PathVariable String id) {
		Optional<Post> post = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
		return new ResponseEntity<>(post, post.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PostMapping("/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post data) {
		if (posts.stream().noneMatch(o -> o.getId().equals(data.getId()))) {
			posts.add(data);
		}

		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<Optional<Post>> updatePost(@PathVariable String id, @RequestBody Post data) {
		Optional<Post> foundPost = posts.stream().filter(post -> post.getId().equals(id)).findFirst();
		foundPost.ifPresent(post -> {
			post.setId(data.getId());
			post.setTitle(data.getTitle());
			post.setBody(data.getBody());
		});
		return new ResponseEntity<>(foundPost, foundPost.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	// END

	@DeleteMapping("/posts/{id}")
	public void destroy(@PathVariable String id) {
		posts.removeIf(p -> p.getId().equals(id));
	}
}
