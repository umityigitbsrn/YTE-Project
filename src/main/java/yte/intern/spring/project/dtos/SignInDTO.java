package yte.intern.spring.project.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class SignInDTO {
    @JsonProperty("username")
    public final String username;

    @JsonProperty("password")
    public final String password;

    @JsonCreator
    public SignInDTO(@JsonProperty("username") String username,
                     @JsonProperty("password") String password){
        this.username = username;
        this.password = password;
    }
}
