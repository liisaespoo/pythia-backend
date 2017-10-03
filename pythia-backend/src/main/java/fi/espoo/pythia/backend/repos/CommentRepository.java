package fi.espoo.pythia.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import fi.espoo.pythia.backend.repos.entities.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	Comment findByCommentId(Long id);

}
