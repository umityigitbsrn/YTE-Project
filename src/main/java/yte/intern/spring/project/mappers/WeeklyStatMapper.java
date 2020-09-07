package yte.intern.spring.project.mappers;

import org.mapstruct.Mapper;
import yte.intern.spring.project.dtos.WeeklyStatDTO;
import yte.intern.spring.project.entities.WeeklyStat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeeklyStatMapper {
    WeeklyStatDTO mapToDto(WeeklyStat weeklyStat);

    WeeklyStat mapToEntity(WeeklyStatDTO weeklyStatDTO);

    List<WeeklyStatDTO> mapToDto(List<WeeklyStat> weeklyStatList);

    List<WeeklyStat> mapToEntity(List<WeeklyStatDTO> weeklyStatDTOList);
}
