package fi.espoo.pythia.backend.repos.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
	@SequenceGenerator(name = "book_generator", sequenceName = "book_seq", allocationSize = 50)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
}
