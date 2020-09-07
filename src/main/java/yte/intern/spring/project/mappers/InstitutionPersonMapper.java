package yte.intern.spring.project.mappers;

import org.mapstruct.Mapper;
import yte.intern.spring.project.dtos.PersonDTO;
import yte.intern.spring.project.dtos.PersonWithPasswordDTO;
import yte.intern.spring.project.dtos.SignInDTO;
import yte.intern.spring.project.entities.InstitutionPerson;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstitutionPersonMapper {
    InstitutionPerson mapToEntity(PersonDTO personDTO);

    PersonDTO mapToDto(InstitutionPerson institutionPerson);

    List<InstitutionPerson> mapToEntity(List<PersonDTO> personDTOList);

    List<PersonDTO> mapToDto(List<InstitutionPerson> institutionPersonList);

    InstitutionPerson mapToEntity(PersonWithPasswordDTO personWithPasswordDTO);

    InstitutionPerson mapToEntity(SignInDTO signInDTO);
}
