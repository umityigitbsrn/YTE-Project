package yte.intern.spring.project.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import yte.intern.spring.project.validation.TcKimlikNo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Builder
public class PersonWithPasswordDTO {

    @JsonProperty("firstName")
    public final String firstName;

    @JsonProperty("lastName")
    public final String lastName;

    @JsonProperty("email")
    @Email(message = "Please enter a valid e-mail address!")
    public final String email;

    @JsonProperty("username")
    public final String username;

    @JsonProperty("password")
    public final String password;

    @JsonProperty
    @Size(min = 11, max = 11, message = "TC Kimlik no must be 11 characters long!")
    @TcKimlikNo(message = "TC Kimlik No must be valid!")
    public final String tcKimlikNo;

    @JsonCreator
    public PersonWithPasswordDTO(@JsonProperty("firstName") String firstName,
                     @JsonProperty("lastName") String lastName,
                     @JsonProperty("email") String email,
                     @JsonProperty("username") String username,
                     @JsonProperty("password") String password,
                     @JsonProperty("tcKimlikNo") String tcKimlikNo){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.tcKimlikNo = tcKimlikNo;
    }
}
