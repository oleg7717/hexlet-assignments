package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Transactional
	long deleteByPostId(long postId);

	List<Comment> findByPostId(long postId);
}
