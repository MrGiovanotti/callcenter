package ec.com.nashira.callcenter.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.nashira.callcenter.entities.Action;
import ec.com.nashira.callcenter.repositories.ActionRepository;
import ec.com.nashira.callcenter.services.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	@Override
	@Transactional(readOnly = true)
	public Action findById(Integer id) {
		return actionRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Action> findAll() {
		return (List<Action>) actionRepository.findAll();
	}

	@Override
	@Transactional
	public Action save(Action action) {
		return actionRepository.save(action);
	}

}
