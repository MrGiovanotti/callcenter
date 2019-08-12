package ec.com.nashira.callcenter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.com.nashira.callcenter.entities.Action;

@Repository
public interface ActionRepository extends CrudRepository<Action, Integer> {

}
