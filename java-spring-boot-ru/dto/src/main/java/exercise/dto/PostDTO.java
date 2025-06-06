package exercise.dto;

import exercise.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// BEGIN
@Setter
@Getter
public class PostDTO {
	private long id;
	private String title;
	private String body;
	List<CommentDTO> comments;
}
// END
