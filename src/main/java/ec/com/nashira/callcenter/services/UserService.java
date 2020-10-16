package ec.com.nashira.callcenter.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ec.com.nashira.callcenter.entities.User;

public interface UserService {

  User findById(Integer id);

  User findByUsername(String username);

  User findByIdForNotAdmin(Integer id);

  Page<User> findAll(Pageable pageable);

  Page<User> findAllForNotAdmin(Pageable pageable);

  User save(User user);

  User delete(Integer id);

  List<User> findAllByNameContaining(String name);

  List<User> findAllByNameContainingForNotAdmin(String name);

}
