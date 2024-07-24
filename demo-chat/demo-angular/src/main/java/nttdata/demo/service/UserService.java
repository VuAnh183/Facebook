package nttdata.demo.service;

import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import nttdata.demo.config.JwtTokenProvider;
import nttdata.demo.entity.*;
import nttdata.demo.exception.CustomException;
import nttdata.demo.mapper.UserMapper;
import nttdata.demo.model.*;
import nttdata.demo.repositories.RoleRepository;
import nttdata.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse Login(LoginRequest loginRequest) throws Exception {
        try {
            User user = userRepository.findByUserName(loginRequest.getUsername());
            if (user == null) {
                throw new CustomException("User not found");
            }
            if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
                LoginResponse response = new LoginResponse();
                var authorities = getAuthorities(user.getRoles());
                String token = jwtTokenProvider.createToken(loginRequest.getUsername(), authorities);
                response.setFullName(user.getFullName());
                response.setUserName(user.getUserName());
                response.setPhoneNumber(user.getPhoneNumber());
                response.setToken(token);
                return response;
            }
            throw new CustomException("Password is incorrect");
        }
        catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    public UserResponse create(UserRequest userRequest) {
        try {
            String passwordEncode = bCryptPasswordEncoder.encode(userRequest.getPassword());
            User user = UserMapper.MAPPER.modelToEntity(userRequest);
            user.setPassword(passwordEncode);
            if(userRequest.getRoles() != null) {
                List<Role> roles = new ArrayList<>();
                for (RoleModel roleModel : userRequest.getRoles()) {
                    Role role = roleRepository.findByName(roleModel.getName());
                    if (role != null) roles.add(role);
                }
                user.setRoles(roles);
            }
            User userSaved = userRepository.save(user);
            UserResponse response = UserMapper.MAPPER.entityToModel(userSaved);
            return response;
        } catch (Exception ex) {
            throw new InternalException(ex.getMessage());
        }
    }

    public List<UserResponse> GetList() {
        var userList = userRepository.findAll();
        return userList.stream().map(UserMapper.MAPPER::entityToModel).collect(Collectors.toList());
    }

    private List<String> getAuthorities(
            List<Role> roles) {
        if (roles == null) return new ArrayList<>();

        List<String> roleAndPrivilege = new ArrayList<>();
        for (Role role: roles) {
            roleAndPrivilege.add("ROLE_" + role.getName());
            for (Privilege pvl: role.getPrivileges()) {
                roleAndPrivilege.add(pvl.getName());
            }
        }

        return roleAndPrivilege;
    }
}
