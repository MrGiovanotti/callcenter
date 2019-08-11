package ec.com.nashira.callcenter.services;

import ec.com.nashira.callcenter.entities.User;

public interface UserService {

	User findByUsernameAndDeleted(String username, boolean deleted);

}
