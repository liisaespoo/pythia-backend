package fi.espoo.pythia.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.espoo.pythia.backend.repos.entities.Plan;

public interface ProjectMappingRepository extends JpaRepository<Plan, Long>{

}
