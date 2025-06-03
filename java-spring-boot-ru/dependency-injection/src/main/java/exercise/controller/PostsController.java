package exercise.controller;

// BEGIN

import exercise.exception.ResourceNotFoundException;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
	@Autowired
	PostRepository postRepository;
	@Autowired
	CommentRepository commentRepository;

	@GetMapping("")
	public List<Post> getPosts() throws NumberFormatException {
		return postRepository.findAll();
	}

	@GetMapping("/{id}")
	public Post getPostsById(@PathVariable long id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Post createPosts(@RequestBody Post data) {
		return postRepository.save(data);
	}

	@PutMapping("/{id}")
	public Post updatePost(@PathVariable long id, @RequestBody Post data) {
		return postRepository.findById(id).map(post -> {
					post.setTitle(data.getTitle());
					post.setBody(data.getBody());
					return postRepository.save(post);
				})
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
	}

	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable long id) {
		postRepository.deleteById(id);
		commentRepository.deleteByPostId(id);
	}
}
// END
