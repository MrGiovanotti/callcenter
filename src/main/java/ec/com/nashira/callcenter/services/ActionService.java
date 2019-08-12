package ec.com.nashira.callcenter.services;

import java.util.List;

import ec.com.nashira.callcenter.entities.Action;

public interface ActionService {

	Action findById(Integer id);

	List<Action> findAll();

	Action save(Action action);

}
