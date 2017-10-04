package fi.espoo.pythia.backend.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fi.espoo.pythia.backend.repos.entities.Comment;
import fi.espoo.pythia.backend.repos.entities.Plan;


public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	Comment findByCommentId(Long id);

	List<Comment> findByPlan(Plan plan);

}
