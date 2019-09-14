package ec.com.nashira.callcenter.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.nashira.callcenter.entities.Authority;
import ec.com.nashira.callcenter.repositories.AuthorityRepository;
import ec.com.nashira.callcenter.services.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public List<Authority> findAll() {
		return (List<Authority>) authorityRepository.findAll();
	}

}
