package ec.com.nashira.callcenter.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ec.com.nashira.callcenter.entities.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

  List<Authority> findByNameNot(String name);

}
