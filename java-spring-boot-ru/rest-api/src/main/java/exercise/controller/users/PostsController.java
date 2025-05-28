package exercise.controller.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
public class PostsController {
	private List<Post> posts = Data.getPosts();

	@GetMapping("/api/users/{id}/posts")
	public List<Post> getPostsById(@PathVariable String id) throws NumberFormatException {
			return posts.stream().filter(post -> post.getUserId() == Integer.parseInt(id)).collect(Collectors.toList());
	}

	@PostMapping("/api/users/{id}/posts")
	@ResponseStatus(HttpStatus.CREATED)
	public Post createPost(@PathVariable String id, @RequestBody Post data) throws NumberFormatException {
		if (posts.stream().noneMatch(o -> o.getSlug().equals(data.getSlug()))) {
			data.setUserId(Integer.parseInt(id));
			posts.add(data);
		}

		return data;
	}
}
// END
