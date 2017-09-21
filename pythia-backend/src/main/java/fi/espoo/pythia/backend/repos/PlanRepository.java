package fi.espoo.pythia.backend.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.espoo.pythia.backend.repos.entities.Plan;
import fi.espoo.pythia.backend.repos.entities.Project;

public interface PlanRepository extends JpaRepository<Plan, Long> {

	List<Plan> findByProject(Project project);
}
