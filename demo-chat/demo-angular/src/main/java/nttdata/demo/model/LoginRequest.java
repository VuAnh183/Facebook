package nttdata.demo.model;

import lombok.Getter;
import lombok.Setter;
import nttdata.demo.validator.EmptyRequired;

@Getter
@Setter
public class LoginRequest {
    @EmptyRequired(name = "username")
    private String username;
    @EmptyRequired(name = "password")
    private String password;
}
