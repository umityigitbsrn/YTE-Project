package yte.intern.spring.project.mappers;

import org.mapstruct.Mapper;
import yte.intern.spring.project.dtos.ActivityDTO;
import yte.intern.spring.project.dtos.ActivityIdDTO;
import yte.intern.spring.project.entities.Activity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity mapToEntity(ActivityDTO activityDTO);

    ActivityDTO mapToDto(Activity activity);

    List<Activity> mapToEntity(List<ActivityDTO> activityDTOList);

    List<ActivityDTO> mapToDto(List<Activity> activityList);

    List<ActivityIdDTO> mapToDtoId(List<Activity> activityList);
}
