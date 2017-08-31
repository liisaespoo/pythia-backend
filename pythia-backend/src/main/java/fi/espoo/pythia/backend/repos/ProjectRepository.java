package fi.espoo.pythia.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.espoo.pythia.backend.repos.entities.User;

public interface ProjectRepository extends JpaRepository<User, Long> {

}
