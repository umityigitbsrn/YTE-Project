package yte.intern.spring.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import yte.intern.spring.project.commonlyUsed.forEntities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "ACTIVITY_SEQ")
public class Activity extends BaseEntity {

    @Column(name = "activity_name")
    private String name;

    @Column(name = "activity_start_date")
    private LocalDateTime startDate;

    @Column(name = "activity_end_date")
    private LocalDateTime endDate;

    @Column(name = "activity_quota")
    private Long quota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_person_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InstitutionPerson institutionPerson;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
    @JoinTable(name = "enrolled_person_activity",
                joinColumns = {@JoinColumn(name = "activity_id")},
                inverseJoinColumns = {@JoinColumn(name = "enrolled_user_id")})
    private Set<EnrolledPerson> enrolledPersons = new HashSet<>();

    public boolean hasEnrolledPerson(String tcKimlikNo){
        return enrolledPersons.stream().anyMatch(enrolledPerson -> enrolledPerson.getTcKimlikNo().equals(tcKimlikNo));
    }
}
