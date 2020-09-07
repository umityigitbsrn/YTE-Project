package yte.intern.spring.project.services;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.webresources.ExtractingRoot;
import org.mapstruct.ap.shaded.freemarker.ext.beans._BeansAPI;
import org.springframework.stereotype.Service;
import yte.intern.spring.project.commonlyUsed.forDTOs.ResponseMessage;
import yte.intern.spring.project.dtos.StatsOfActivitiesDTO;
import yte.intern.spring.project.entities.Activity;
import yte.intern.spring.project.entities.InstitutionPerson;
import yte.intern.spring.project.entities.WeeklyStat;
import yte.intern.spring.project.repositories.ActivityRepository;
import yte.intern.spring.project.repositories.InstitutionPersonRepository;
import yte.intern.spring.project.repositories.WeeklyStatRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static yte.intern.spring.project.commonlyUsed.forDTOs.MessageType.SUCCESS;
import static yte.intern.spring.project.commonlyUsed.forDTOs.MessageType.ERROR;

@Service
@RequiredArgsConstructor
public class InstitutionPersonService {
    private final InstitutionPersonRepository institutionPersonRepository;
    private final ActivityRepository activityRepository;
    private final WeeklyStatRepository weeklyStatRepository;

    //CRUD for Institution users
    public List<InstitutionPerson> listAllInstitutionPerson() {
        return institutionPersonRepository.findAll();
    }

    public InstitutionPerson getInstitutionPersonByUsername(String username) {
        return institutionPersonRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public ResponseMessage addInstitutionPerson(InstitutionPerson institutionPerson) {
        institutionPersonRepository.save(institutionPerson);
        return new ResponseMessage("Institution user has been added successfully!", SUCCESS);
    }

    public ResponseMessage updateInstitutionPerson(String username, InstitutionPerson institutionPerson){
        Optional<InstitutionPerson> personOptional = institutionPersonRepository.findByUsername(username);

        if (personOptional.isPresent()){
            InstitutionPerson personFromDB = personOptional.get();
            personFromDB.setFirstName(institutionPerson.getFirstName());
            personFromDB.setLastName(institutionPerson.getLastName());
            personFromDB.setUsername(institutionPerson.getUsername());
            personFromDB.setEmail(institutionPerson.getEmail());
            institutionPersonRepository.save(personFromDB);
            return new ResponseMessage(String.format("Institution user with %s username has been updated successfully!", username), SUCCESS);
        }
        return new ResponseMessage(String.format("Institution user with %s username cannot be found so that update request failed!", username), ERROR);
    }

    public ResponseMessage deleteInstitutionPerson(String username){
        if (institutionPersonRepository.existsByUsername(username)){
            institutionPersonRepository.deleteByUsername(username);
            return new ResponseMessage(String.format("Institution user with %s username has been deleted successfully!", username), SUCCESS);
        }
        return new ResponseMessage(String.format("Institution user with %s username cannot be found so that delete request failed!", username), ERROR);
    }

    //CRUD for Institution users' activities
    public List<Activity> listInstitutionPersonActivities(String username){
        return activityRepository.findByInstitutionPerson_Username(username);
    }

    public ResponseMessage addActivityToInstitutionPerson(String username, Activity activity){
        Optional<InstitutionPerson> personOptional = institutionPersonRepository.findByUsername(username);
        if (personOptional.isPresent()){
            Optional<ResponseMessage> responseMessage = personOptional.map(institutionPerson -> {
                activity.setInstitutionPerson(institutionPerson);
                Activity savedActivity = activityRepository.save(activity);
                weeklyStatRepository.save(new WeeklyStat(savedActivity.getId(), 0L, 0L, 0L, 0L, 0L, 0L, 0L));
                return new ResponseMessage(String.format("Activity has been added successfully to institution user with username %s", username), SUCCESS);
            });
            return responseMessage.get();
        }
        return new ResponseMessage(String.format("Adding activity request failed because institution user with username %s cannot be found!", username), ERROR);
    }

    public ResponseMessage updateActivityForInstitutionPerson(String username, Long activityId, Activity activity){
        Optional<Activity> activityOptional = activityRepository.findByIdAndInstitutionPerson_Username(activityId, username);
        if(activityOptional.isPresent()){
            Activity activityFromDB = activityOptional.get();
            if (activityFromDB.getEndDate().isAfter(LocalDateTime.now())){
                activityFromDB.setName(activity.getName());
                activityFromDB.setStartDate(activity.getStartDate());
                activityFromDB.setEndDate(activity.getEndDate());
                activityFromDB.setQuota(activity.getQuota());
                activityRepository.save(activityFromDB);
                return new ResponseMessage(String.format("Activity with id %d has been updated successfully for institution user with username %s", activityId, username), SUCCESS);
            }
            return new ResponseMessage(String.format("Update activity request failed because activity with id %d's end date is after now!", activityId), ERROR);
        }
        return new ResponseMessage(String.format("Update activity request failed because activity with id %d or institution user with username %s cannot be found!", activityId, username), ERROR);
    }

    public ResponseMessage deleteActivityFromInstitutionPerson(String username, Long activityId){
        Optional<Activity> activityOptional = activityRepository.findByIdAndInstitutionPerson_Username(activityId, username);
        if(activityOptional.isPresent()){
            if (activityOptional.get().getEndDate().isAfter(LocalDateTime.now())) {
                weeklyStatRepository.delete(weeklyStatRepository.findByActivityId(activityId));
                Optional<ResponseMessage> responseMessage = activityOptional.map(activity -> {
                    activityRepository.delete(activity);
                    return new ResponseMessage(String.format("Activity with id %d has been deleted successfully from institution user with username %s", activityId, username), SUCCESS);
                });
                return responseMessage.get();
            }
            return new ResponseMessage(String.format("Delete activity request failed because activity with id %d's end date is after now!", activityId), ERROR);
        }
        return new ResponseMessage(String.format("Delete activity request failed because activity with id %d or institution user with username %s cannot be found!", activityId, username), ERROR);
    }

    public List<StatsOfActivitiesDTO> getStatsOfActivities(String username){
        return  activityRepository.findByInstitutionPerson_Username(username).stream()
                .map(activity -> new StatsOfActivitiesDTO(activity.getName(), activity.getEnrolledPersons().size()))
                .collect(Collectors.toList());
    }

    public ResponseMessage checkInstitutionPerson(InstitutionPerson institutionPerson){
        if(institutionPersonRepository.existsByUsernameAndPassword(institutionPerson.getUsername(), institutionPerson.getPassword()))
            return new ResponseMessage("Institution person is found!", SUCCESS);
        return new ResponseMessage("Institution person cannot be found!", ERROR);
    };
}
