package exercise.controller;

// BEGIN
import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
	@Autowired
	CommentRepository commentRepository;
	@GetMapping("")
	public List<Comment> getComments() throws NumberFormatException {
		return commentRepository.findAll();
	}

	@GetMapping("/{id}")
	public Comment getCommentsById(@PathVariable long id) {
		return commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Comment createComments(@RequestBody Comment data) {
		return commentRepository.save(data);
	}

	@PutMapping("/{id}")
	public Comment updateComment(@PathVariable long id, @RequestBody Comment data) {
		return commentRepository.findById(id).map(comment -> {
					comment.setPostId(data.getPostId());
					comment.setBody(data.getBody());
					return commentRepository.save(comment);
				})
				.orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
	}

	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable long id) {
		commentRepository.deleteById(id);
	}
}
// END
