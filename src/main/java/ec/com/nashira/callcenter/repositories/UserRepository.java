package ec.com.nashira.callcenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.nashira.callcenter.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndDeleted(String username, boolean deleted);

}
