package yte.intern.spring.project.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class StatsOfActivitiesDTO {
    @JsonProperty("activityName")
    public final String activityName;

    @JsonProperty("size")
    public final Integer size;

    @JsonCreator
    public StatsOfActivitiesDTO(@JsonProperty("activityName") String activityName,
                                @JsonProperty("size") Integer size){
        this.activityName = activityName;
        this.size = size;
    }
}
