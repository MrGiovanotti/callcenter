package ec.com.nashira.callcenter.auth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.repositories.UserRepository;

public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameAndDeleted(username, false);
		if (user == null) {
			throw new UsernameNotFoundException("El usuario " + username + " no existe");
		}
		List<GrantedAuthority> authorities = user.getAuthorities().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getName())).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(),
				true, true, true, authorities);
	}

}
