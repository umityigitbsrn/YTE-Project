package yte.intern.spring.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.project.entities.Activity;
import yte.intern.spring.project.entities.EnrolledPerson;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByInstitutionPerson_Username(String username);
    Optional<Activity> findByIdAndInstitutionPerson_Username(Long id, String username);
}
