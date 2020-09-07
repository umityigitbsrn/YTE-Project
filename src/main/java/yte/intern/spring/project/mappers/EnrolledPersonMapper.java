package yte.intern.spring.project.mappers;

import org.mapstruct.Mapper;
import yte.intern.spring.project.dtos.PersonDTO;
import yte.intern.spring.project.entities.EnrolledPerson;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrolledPersonMapper {
    EnrolledPerson mapToEntity(PersonDTO personDTO);

    PersonDTO mapToDto(EnrolledPerson enrolledPerson);

    List<EnrolledPerson> mapToEntity(List<PersonDTO> personDTOList);

    List<PersonDTO> mapToDto(List<EnrolledPerson> enrolledPeople);
}
