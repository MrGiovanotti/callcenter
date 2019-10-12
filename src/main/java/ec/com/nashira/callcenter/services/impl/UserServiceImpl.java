package ec.com.nashira.callcenter.services.impl;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ec.com.nashira.callcenter.AppProperties;
import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.repositories.UserRepository;
import ec.com.nashira.callcenter.services.UserService;
import ec.com.nashira.callcenter.utils.FilesUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AppProperties properties;

  @Override
  @Transactional(readOnly = true)
  public User findByUsername(String username) {
    return userRepository.findByUsernameAndDeleted(username, false);
  }

  @Override
  @Transactional(readOnly = true)
  public User findById(Integer id) {
    return userRepository.findByIdAndDeleted(id, false);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  @Transactional
  public User delete(Integer id) {
    User user = findById(id);
    if (user == null) {
      return null;
    }
    String filePath = properties.getImagesPath();
    FilesUtils.deleteFile(user.getImage(), filePath);
    user.setDeleted(true);
    user.setUsername(UUID.randomUUID().toString().concat(user.getId().toString()));
    user.setImage(null);
    return userRepository.save(user);
  }

}
