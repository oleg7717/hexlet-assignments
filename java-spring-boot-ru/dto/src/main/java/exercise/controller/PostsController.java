package exercise.controller;

// BEGIN
import exercise.dto.PostDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapping.MappingUtils;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/posts")
public class PostsController {
	private final MappingUtils mappingUtils = new MappingUtils();
	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	@GetMapping("")
	@ResponseBody
	public List<PostDTO> index() {
		return postRepository.findAll().stream()
				.map(this::toPostDto)
				.collect(toList());
	}

	@GetMapping("/{id}")
	@ResponseBody
	public PostDTO show(@PathVariable String id) {
		long postId;
		try {
			 postId = Long.parseLong(id);
		} catch (NumberFormatException ex) {
			throw new NumberFormatException("Incorrect id " + id + ". Must be number of post.");
		}

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found."));

		return toPostDto(post);
	}

	private PostDTO toPostDto(Post post) {
		return mappingUtils.mapToPostDto(post, commentRepository.findByPostId(post.getId()));
	}
}
// END
