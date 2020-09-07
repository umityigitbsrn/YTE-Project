package yte.intern.spring.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.project.entities.InstitutionPerson;

import javax.transaction.Transactional;
import java.util.Optional;

public interface InstitutionPersonRepository extends JpaRepository<InstitutionPerson, Long> {
    Optional<InstitutionPerson> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    @Transactional
    void deleteByUsername(String username);
}
