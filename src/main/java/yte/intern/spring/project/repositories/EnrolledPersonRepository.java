package yte.intern.spring.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.project.entities.EnrolledPerson;

import java.util.Optional;

public interface EnrolledPersonRepository extends JpaRepository<EnrolledPerson, Long> {
    Optional<EnrolledPerson> findByUsername(String username);
}
