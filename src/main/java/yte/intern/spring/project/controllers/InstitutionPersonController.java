package yte.intern.spring.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.project.commonlyUsed.forDTOs.ResponseMessage;
import yte.intern.spring.project.dtos.*;
import yte.intern.spring.project.entities.Activity;
import yte.intern.spring.project.entities.InstitutionPerson;
import yte.intern.spring.project.mappers.ActivityMapper;
import yte.intern.spring.project.mappers.InstitutionPersonMapper;
import yte.intern.spring.project.services.InstitutionPersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/institution_person")
@RequiredArgsConstructor
@Validated
public class InstitutionPersonController {
    private final InstitutionPersonService institutionPersonService;
    private final InstitutionPersonMapper institutionPersonMapper;
    private final ActivityMapper activityMapper;

    @GetMapping
    public List<PersonDTO> listAllInstitutionPerson(){
        List<InstitutionPerson> institutionPeople = institutionPersonService.listAllInstitutionPerson();
        return institutionPersonMapper.mapToDto(institutionPeople);
    }

    @GetMapping("/{username}")
    public PersonDTO getInstitutionPersonByUsername(@PathVariable String username){
        InstitutionPerson institutionPersonByUsername = institutionPersonService.getInstitutionPersonByUsername(username);
        return institutionPersonMapper.mapToDto(institutionPersonByUsername);
    }

    @GetMapping("/{username}/stats")
    public List<StatsOfActivitiesDTO> getStatsOfActivities(@PathVariable String username){
        return institutionPersonService.getStatsOfActivities(username);
    }

    @PostMapping
    public ResponseMessage addInstitutionPerson(@Valid @RequestBody PersonWithPasswordDTO personDTO){
        InstitutionPerson institutionPerson = institutionPersonMapper.mapToEntity(personDTO);
        return institutionPersonService.addInstitutionPerson(institutionPerson);
    }

    @PostMapping("/sign_in")
    public ResponseMessage checkInstitutionPerson(@RequestBody SignInDTO signInDTO){
        InstitutionPerson institutionPerson = institutionPersonMapper.mapToEntity(signInDTO);
        return institutionPersonService.checkInstitutionPerson(institutionPerson);
    }

    @PutMapping("/{username}")
    public ResponseMessage updateInstitutionPerson(@PathVariable String username,@Valid @RequestBody PersonDTO personDTO){
        InstitutionPerson institutionPerson = institutionPersonMapper.mapToEntity(personDTO);
        return institutionPersonService.updateInstitutionPerson(username, institutionPerson);
    }

    @DeleteMapping("/{username}")
    public ResponseMessage deleteInstitutionPerson(@PathVariable String username){
        return institutionPersonService.deleteInstitutionPerson(username);
    }

    @GetMapping("/{username}/activity")
    public List<ActivityIdDTO> listInstitutionPersonActivities(@PathVariable String username){
        List<Activity> activities = institutionPersonService.listInstitutionPersonActivities(username);
        return activityMapper.mapToDtoId(activities);
    }

    @PostMapping("/{username}/activity")
    public ResponseMessage addActivityToInstitutionPerson(@PathVariable String username,@Valid @RequestBody ActivityDTO activityDTO){
        Activity activity = activityMapper.mapToEntity(activityDTO);
        return institutionPersonService.addActivityToInstitutionPerson(username, activity);
    }

    @PutMapping("/{username}/activity/{activityId}")
    public ResponseMessage updateActivityForInstitutionPerson(@PathVariable String username,
                                                              @PathVariable Long activityId,
                                                              @Valid @RequestBody ActivityDTO activityDTO){
        Activity activity = activityMapper.mapToEntity(activityDTO);
        return institutionPersonService.updateActivityForInstitutionPerson(username, activityId, activity);
    }

    @DeleteMapping("/{username}/activity/{activityId}")
    public ResponseMessage deleteActivityFromInstitutionPerson(@PathVariable String username,
                                                               @PathVariable Long activityId){
        return institutionPersonService.deleteActivityFromInstitutionPerson(username, activityId);
    }
}
