package nttdata.demo.model;

import lombok.Getter;
import lombok.Setter;
import nttdata.demo.validator.EmptyRequired;
import nttdata.demo.validator.PatternRequired;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class UserRequest {
    @EmptyRequired(name = "userName")
    @JsonProperty("username")
    private String userName;
    @EmptyRequired(name = "fullName")
    @JsonProperty("full_name")
    private String fullName;
    @EmptyRequired(name = "password")
    private String password;
    private String phoneNumber;
    @PatternRequired(name = "email", regexp = "^(.+)@(.+)$")
    private String email;
    private List<RoleModel> roles;
}


