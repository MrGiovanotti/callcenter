package ec.com.nashira.callcenter.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.repositories.UserRepository;
import ec.com.nashira.callcenter.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findByUsernameAndDeleted(String username, boolean deleted) {
		return userRepository.findByUsernameAndDeleted(username, deleted);
	}

}
