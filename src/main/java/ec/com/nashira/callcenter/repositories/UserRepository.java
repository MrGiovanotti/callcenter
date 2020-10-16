package ec.com.nashira.callcenter.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ec.com.nashira.callcenter.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByIdAndDeleted(Integer id, boolean deleted);

  User findByUsernameAndDeleted(String username, boolean deleted);

  @Override
  @Query("SELECT u FROM User u WHERE u.deleted = false")
  Page<User> findAll(Pageable pageable);

  @Query("SELECT u FROM User u WHERE u.authority.name != 'ROLE_ADMIN' AND u.deleted = false")
  Page<User> findAllForNotAdmin(Pageable pageable);

  @Query("SELECT u FROM User u WHERE u.authority.name != 'ROLE_ADMIN' AND u.deleted = false and u.id = ?1")
  User findByIdForNotAdmin(Integer id);

  List<User> findAllByNameContainingAndDeleted(String name, boolean deleted);

  @Query("SELECT u FROM User u WHERE u.authority.name != 'ROLE_ADMIN' AND u.deleted = false and u.name LIKE CONCAT('%',:name,'%')")
  List<User> findAllByNameContainingForNotAdmin(String name);

}
