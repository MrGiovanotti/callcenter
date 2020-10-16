package ec.com.nashira.callcenter.auth.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ec.com.nashira.callcenter.entities.Authority;
import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.logger.Logger;
import ec.com.nashira.callcenter.repositories.UserRepository;
import ec.com.nashira.callcenter.utils.ConstantsUtils;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Logger log;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsernameAndDeleted(username, false);
    if (user == null) {
      String message = ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE
          .concat(ConstantsUtils.COLON_SEPARATOR).concat(username);
      log.writeLog(message);
      throw new UsernameNotFoundException(message);
    }

    Authority authority = user.getAuthority();
    List<GrantedAuthority> authorities =
        Arrays.asList(new SimpleGrantedAuthority(authority.getName()));

    return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
        user.isEnabled(), true, true, true, authorities);
  }

}
