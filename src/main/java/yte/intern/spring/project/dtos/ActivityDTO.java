package yte.intern.spring.project.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Builder
public class ActivityDTO {

    @JsonProperty("name")
    public final String name;

    @JsonProperty("startDate")
    @Future(message = "Starting date of the activity should be in the future!")
    public final LocalDateTime startDate;

    @JsonProperty("endDate")
    public final LocalDateTime endDate;

    @JsonProperty("quota")
    public final Long quota;

    @AssertTrue(message = "End date must be after than starting date of the activity!")
    public boolean isEndDateValid(){
        return startDate.isBefore(endDate);
    }

    @JsonCreator
    public ActivityDTO(@JsonProperty("name") String name,
                       @JsonProperty("startDate") LocalDateTime startDate,
                       @JsonProperty("endDate") LocalDateTime endDate,
                       @JsonProperty("quota") Long quota){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quota = quota;
    }
}
