package nttdata.demo.mapper;

import nttdata.demo.model.UserRequest;
import nttdata.demo.model.UserResponse;
import nttdata.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    public UserMapper  MAPPER = Mappers.getMapper(UserMapper.class);
    User modelToEntity(UserRequest userRequest);

    UserResponse entityToModel(User user);
}
