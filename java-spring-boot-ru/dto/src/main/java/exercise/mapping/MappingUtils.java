package exercise.mapping;

import exercise.dto.CommentDTO;
import exercise.dto.PostDTO;
import exercise.model.Comment;
import exercise.model.Post;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MappingUtils {
	public PostDTO mapToPostDto(Post post, List<Comment> comment){
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getId());
		postDTO.setTitle(post.getTitle());
		postDTO.setBody(post.getBody());
		List<CommentDTO> comments = comment
				.stream()
				.map(this::mapToCommentDto)
				.collect(toList());
		postDTO.setComments(comments);

		return postDTO;
	}

	public CommentDTO mapToCommentDto(Comment comment){
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setBody(comment.getBody());

		return commentDTO;
	}

	public Post mapToPost(PostDTO dto){
		Post post = new Post();
		post.setId(dto.getId());
		post.setTitle(dto.getTitle());
		post.setBody(dto.getBody());
		return post;
	}
}
