package yte.intern.spring.project.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Builder
public class ActivityIdDTO {

    @JsonProperty("id")
    public final Long id;

    @JsonProperty("name")
    public final String name;

    @JsonProperty("startDate")
    public final LocalDateTime startDate;

    @JsonProperty("endDate")
    public final LocalDateTime endDate;

    @JsonProperty("quota")
    public final Long quota;

    @JsonCreator
    public ActivityIdDTO(@JsonProperty("id") Long id,
                         @JsonProperty("name") String name,
                         @JsonProperty("startDate") LocalDateTime startDate,
                         @JsonProperty("endDate") LocalDateTime endDate,
                         @JsonProperty("quota") Long quota){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quota = quota;
    }
}
