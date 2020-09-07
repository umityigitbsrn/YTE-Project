package yte.intern.spring.project.entities;

import lombok.*;
import yte.intern.spring.project.commonlyUsed.forEntities.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "ENROLLED_PERSON_SEQ")
public class EnrolledPerson extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "tc_kimlik_no")
    private String tcKimlikNo;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "enrolledPersons")
    private Set<Activity> activities = new HashSet<>();

}
