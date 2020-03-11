package ec.com.nashira.callcenter.repositories;

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

  @Query("SELECT u FROM User u inner join u.authorities auth where auth.name != 'ROLE_ADMIN' AND u.deleted = false")
  Page<User> findNotAdmin(Pageable pageable);

}
