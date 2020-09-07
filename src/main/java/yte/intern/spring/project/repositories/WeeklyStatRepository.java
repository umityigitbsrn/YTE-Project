package yte.intern.spring.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring.project.entities.WeeklyStat;

public interface WeeklyStatRepository extends JpaRepository<WeeklyStat, Long> {
    WeeklyStat findByActivityId(Long activityId);
}
