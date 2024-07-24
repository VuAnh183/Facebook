package nttdata.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String userName;
    private String fullName;
    private String phoneNumber;
    private String token;
}
