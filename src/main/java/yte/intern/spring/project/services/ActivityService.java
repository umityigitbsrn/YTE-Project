package yte.intern.spring.project.services;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import yte.intern.spring.project.commonlyUsed.forDTOs.ResponseMessage;
import yte.intern.spring.project.entities.Activity;
import yte.intern.spring.project.entities.EnrolledPerson;
import yte.intern.spring.project.entities.WeeklyStat;
import yte.intern.spring.project.repositories.ActivityRepository;
import yte.intern.spring.project.repositories.EnrolledPersonRepository;
import yte.intern.spring.project.repositories.WeeklyStatRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static yte.intern.spring.project.commonlyUsed.forDTOs.MessageType.SUCCESS;
import static yte.intern.spring.project.commonlyUsed.forDTOs.MessageType.ERROR;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final EnrolledPersonRepository enrolledPersonRepository;
    private final WeeklyStatRepository weeklyStatRepository;

    public List<Activity> listAllActivities() {
        List<Activity> activities = activityRepository.findAll().stream().filter(activity -> activity.getStartDate().isAfter(LocalDateTime.now())).collect(Collectors.toList());
        System.out.println(activities);
        return activities;
    }

    public Activity getActivityById(Long activityId){
        return activityRepository.findById(activityId).orElseThrow(EntityNotFoundException::new);
    }

    public ResponseMessage addActivity(Activity activity){
        activityRepository.save(activity);
        return new ResponseMessage("Activity has been added successfully!", SUCCESS);
    }

    @Transactional
    public ResponseMessage updateActivity(Long activityId, Activity activity){
        Optional<Activity> activityFromDBOp = activityRepository.findById(activityId);
        if (activityFromDBOp.isPresent()){
            Activity activityFromDB = activityFromDBOp.get();
            activityFromDB.setName(activity.getName());
            activityFromDB.setStartDate(activity.getStartDate());
            activityFromDB.setEndDate(activity.getEndDate());
            activityFromDB.setQuota(activity.getQuota());
            activityRepository.save(activityFromDB);
            return new ResponseMessage(String.format("Activity with id %s has been updated successfully!", activityId), SUCCESS);
        }
        return new ResponseMessage(String.format("Activity with id %s cannot found so that update request failed!", activityId), ERROR);
    }

    public ResponseMessage deleteActivity(Long activityId){
        if (activityRepository.existsById(activityId)){
            activityRepository.deleteById(activityId);
            return new ResponseMessage(String.format("Activity with id %s has been deleted successfully!", activityId), SUCCESS);
        }
        return new ResponseMessage(String.format("Activity with id %s cannot be found so that delete request failed!", activityId), ERROR);
    }

    public List<EnrolledPerson> listAllEnrolledPeopleActivity(Long activityId){
        return activityRepository.findById(activityId).map(activity -> {
            return new ArrayList<>(activity.getEnrolledPersons());
        }).orElseThrow(EntityNotFoundException::new);
    }

    public ResponseMessage addEnrolledPersonToActivity(Long activityId, EnrolledPerson enrolledPerson){
        Optional<Activity> activityOptional = activityRepository.findById(activityId);
        Optional<EnrolledPerson> enrolledPersonOptional = enrolledPersonRepository.findByUsername(enrolledPerson.getUsername());
        if (activityOptional.isPresent()){
            Activity activity = activityOptional.get();
            if(activity.getQuota() > 0) {
                if (!enrolledPersonOptional.isPresent()) {
                    activity.getEnrolledPersons().add(enrolledPerson);
                    enrolledPerson.getActivities().add(activity);
                } else {
                    EnrolledPerson enrolledPersonFromDB = enrolledPersonOptional.get();
                    if(!activity.hasEnrolledPerson(enrolledPersonFromDB.getTcKimlikNo())) {
                        activity.getEnrolledPersons().add(enrolledPersonFromDB);
                        enrolledPersonFromDB.getActivities().add(activity);
                    } else {
                        return new ResponseMessage(String.format("Adding person request failed because activity with" +
                                        " id %d has already have person with username %s!", activityId,
                                        enrolledPersonFromDB.getUsername()), ERROR);
                    }
                }
                activity.setQuota(activity.getQuota() - 1);
                Activity updatedActivity = activityRepository.save(activity);

                //Weekly Stats
                WeeklyStat weeklyStat = weeklyStatRepository.findByActivityId(activityId);
                String dayOfWeek = updatedActivity.getLastModified().getDayOfWeek().name();
                if (dayOfWeek.equals("MONDAY"))
                    weeklyStat.setMonday(weeklyStat.getMonday() + 1);
                else if (dayOfWeek.equals("TUESDAY"))
                    weeklyStat.setTuesday(weeklyStat.getTuesday() + 1);
                else if (dayOfWeek.equals("WEDNESDAY"))
                    weeklyStat.setWednesday(weeklyStat.getWednesday() + 1);
                else if (dayOfWeek.equals("THURSDAY"))
                    weeklyStat.setThursday(weeklyStat.getThursday() + 1);
                else if (dayOfWeek.equals("FRIDAY"))
                    weeklyStat.setFriday(weeklyStat.getFriday() + 1);
                else if (dayOfWeek.equals("SATURDAY"))
                    weeklyStat.setSaturday(weeklyStat.getSaturday() + 1);
                else if (dayOfWeek.equals("SUNDAY"))
                    weeklyStat.setSunday(weeklyStat.getSunday() + 1);

                weeklyStatRepository.save(weeklyStat);

                return new ResponseMessage(String.format("Person with username %s is added to activity with id %d successfully!", enrolledPerson.getUsername(), activityId), SUCCESS);
            }
            return new ResponseMessage(String.format("Adding person request failed because activity with id %d is full!", activityId), ERROR);
        }
        return new ResponseMessage(String.format("Adding person request failed because activity with id %d cannot be found!", activityId), ERROR);
    }

    public WeeklyStat getWeeklyStatOfActivity(Long activityId){
        return weeklyStatRepository.findByActivityId(activityId);
    }

}
