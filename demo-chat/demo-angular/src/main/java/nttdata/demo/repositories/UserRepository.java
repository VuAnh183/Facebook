package nttdata.demo.repositories;

import nttdata.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>  {
    User findByUserName(String userName);
}
