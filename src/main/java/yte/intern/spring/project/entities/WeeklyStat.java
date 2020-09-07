package yte.intern.spring.project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.intern.spring.project.commonlyUsed.forEntities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "ACTIVITY_SEQ")
public class WeeklyStat extends BaseEntity {

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "monday")
    private Long monday;

    @Column(name = "tuesday")
    private Long tuesday;

    @Column(name = "wednesday")
    private Long wednesday;

    @Column(name = "thursday")
    private Long thursday;

    @Column(name = "friday")
    private Long friday;

    @Column(name = "saturday")
    private Long saturday;

    @Column(name = "sunday")
    private Long sunday;
}
