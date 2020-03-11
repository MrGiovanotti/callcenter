package ec.com.nashira.callcenter.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ec.com.nashira.callcenter.entities.User;

public interface UserService {

  User findById(Integer id);

  User findByUsername(String username);

  Page<User> findAll(Pageable pageable);

  Page<User> findNotAdmin(Pageable pageable);

  User save(User user);

  User delete(Integer id);

}
