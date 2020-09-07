package yte.intern.spring.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.spring.project.commonlyUsed.forDTOs.ResponseMessage;
import yte.intern.spring.project.dtos.ActivityDTO;
import yte.intern.spring.project.dtos.ActivityIdDTO;
import yte.intern.spring.project.dtos.PersonDTO;
import yte.intern.spring.project.dtos.WeeklyStatDTO;
import yte.intern.spring.project.entities.Activity;
import yte.intern.spring.project.entities.EnrolledPerson;
import yte.intern.spring.project.entities.WeeklyStat;
import yte.intern.spring.project.mappers.ActivityMapper;
import yte.intern.spring.project.mappers.EnrolledPersonMapper;
import yte.intern.spring.project.mappers.WeeklyStatMapper;
import yte.intern.spring.project.services.ActivityService;
import yte.intern.spring.project.services.EmailService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final EmailService emailService;
    private final ActivityMapper activityMapper;
    private final EnrolledPersonMapper enrolledPersonMapper;
    private final WeeklyStatMapper weeklyStatMapper;

    @GetMapping
    public List<ActivityIdDTO> listAllActivities(){
        List<Activity> activityList = activityService.listAllActivities();
        return activityMapper.mapToDtoId(activityList);
    }

    @GetMapping("/{id}")
    public ActivityDTO getActivityById(@PathVariable Long id){
        Activity activityById = activityService.getActivityById(id);
        return activityMapper.mapToDto(activityById);
    }

    @GetMapping("/{id}/stats")
    public WeeklyStatDTO getWeeklyStatOfActivity(@PathVariable Long id){
        return weeklyStatMapper.mapToDto(activityService.getWeeklyStatOfActivity(id));
    }

    @PostMapping
    public ResponseMessage addActivity(@Valid @RequestBody ActivityDTO activityDTO){
        Activity activity = activityMapper.mapToEntity(activityDTO);
        return activityService.addActivity(activity);
    }

    @PutMapping("/{id}")
    public ResponseMessage udpateActivity(@PathVariable Long id, @Valid @RequestBody ActivityDTO activityDTO){
        Activity activity = activityMapper.mapToEntity(activityDTO);
        return activityService.updateActivity(id, activity);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteActivity(@PathVariable Long id){
        return activityService.deleteActivity(id);
    }

    @GetMapping("/{id}/enrolled_person")
    public List<PersonDTO> listAllEnrolledPersonActivity(@PathVariable Long id){
        List<EnrolledPerson> enrolledPeople = activityService.listAllEnrolledPeopleActivity(id);
        return enrolledPersonMapper.mapToDto(enrolledPeople);
    }

    @PostMapping("/{id}/enrolled_person")
    public ResponseMessage addEnrolledPersonToActivity(@PathVariable Long id, @Valid @RequestBody PersonDTO personDTO){
        EnrolledPerson enrolledPerson = enrolledPersonMapper.mapToEntity(personDTO);
        emailService.sendSimpleMessage(enrolledPerson.getEmail());
        return activityService.addEnrolledPersonToActivity(id, enrolledPerson);
    }
}
