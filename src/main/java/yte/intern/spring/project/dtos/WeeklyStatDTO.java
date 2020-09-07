package yte.intern.spring.project.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.persistence.Column;

@Builder
public class WeeklyStatDTO {

    @JsonProperty("monday")
    public final Long monday;

    @JsonProperty("tuesday")
    public final Long tuesday;

    @JsonProperty("wednesday")
    public final Long wednesday;

    @JsonProperty("thursday")
    public final Long thursday;

    @JsonProperty("friday")
    public final Long friday;

    @JsonProperty("saturday")
    public final Long saturday;

    @JsonProperty("sunday")
    public final Long sunday;

    @JsonCreator
    public WeeklyStatDTO(@JsonProperty("monday") Long monday,
                         @JsonProperty("tuesday") Long tuesday,
                         @JsonProperty("wednesday") Long wednesday,
                         @JsonProperty("thursday") Long thursday,
                         @JsonProperty("friday") Long friday,
                         @JsonProperty("saturday") Long saturday,
                         @JsonProperty("sunday") Long sunday){
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

}
