package ec.com.nashira.callcenter.controllers;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ec.com.nashira.callcenter.AppProperties;
import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.entities.dto.ChangePasswordDto;
import ec.com.nashira.callcenter.entities.dto.UserDto;
import ec.com.nashira.callcenter.logger.Logger;
import ec.com.nashira.callcenter.responseobjects.GenericResponse;
import ec.com.nashira.callcenter.services.UserService;
import ec.com.nashira.callcenter.utils.ConstantsUtils;
import ec.com.nashira.callcenter.utils.FilesUtils;

@RestController
@RequestMapping("/user")
public class UserController {

  private static final String PROPERTY_TO_ORDER_BY = "name";

  @Autowired
  private UserService userService;

  @Autowired
  private Logger log;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private AppProperties properties;

  @Secured({"ROLE_ADMIN"})
  @GetMapping("/index/admin/{page}")
  public ResponseEntity<Map<String, Object>> index(@PathVariable("page") int pageNumber) {
    Page<User> users = null;
    try {
      users = userService.findAll(PageRequest.of(pageNumber, ConstantsUtils.NUMBER_ITEMS_PER_PAGE,
          Sort.by(Sort.Direction.ASC, PROPERTY_TO_ORDER_BY)));
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return new GenericResponse("", users, HttpStatus.OK).build();
  }

  @Secured({"ROLE_ADMIN", "ROLE_SUPERVISOR"})
  @GetMapping("/index/{page}")
  public ResponseEntity<Map<String, Object>> getNotAdminUsers(
      @PathVariable("page") int pageNumber) {
    Page<User> users = null;
    try {
      users = userService.findAllForNotAdmin(PageRequest.of(pageNumber,
          ConstantsUtils.NUMBER_ITEMS_PER_PAGE, Sort.by(Sort.Direction.ASC, PROPERTY_TO_ORDER_BY)));
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return new GenericResponse("", users, HttpStatus.OK).build();
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/search/admin/{name}")
  public ResponseEntity<Map<String, Object>> search(@PathVariable String name) {
    List<User> users = null;
    try {
      users = userService.findAllByNameContaining(name);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    return new GenericResponse("", users, HttpStatus.OK).build();
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/search/{name}")
  public ResponseEntity<Map<String, Object>> searchForNotAdmin(@PathVariable String name) {
    List<User> users = null;
    try {
      users = userService.findAllByNameContainingForNotAdmin(name);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    return new GenericResponse("", users, HttpStatus.OK).build();
  }

  @Secured({"ROLE_ADMIN", "ROLE_SUPERVISOR"})
  @GetMapping("/show/{id}")
  public ResponseEntity<Map<String, Object>> show(@PathVariable Integer id) {
    User user = null;
    try {
      user = userService.findByIdForNotAdmin(id);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    if (user == null) {
      return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, user, true,
          HttpStatus.NOT_FOUND).build();
    }
    return new GenericResponse(ConstantsUtils.FOUND_RESOURCE_MESSAGE, user, HttpStatus.OK).build();
  }

  @Secured({"ROLE_ADMIN"})
  @GetMapping("/show/admin/{id}")
  public ResponseEntity<Map<String, Object>> showForNotAdmin(@PathVariable Integer id) {
    User user = null;
    try {
      user = userService.findById(id);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    if (user == null) {
      return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, user, true,
          HttpStatus.NOT_FOUND).build();
    }
    return new GenericResponse(ConstantsUtils.FOUND_RESOURCE_MESSAGE, user, HttpStatus.OK).build();
  }

  @Secured({"ROLE_ADMIN", "ROLE_SUPERVISOR"})
  @PostMapping("/create")
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody UserDto userDto,
      BindingResult result) {
    if (result.hasErrors()) {
      List<String> errors = result
          .getFieldErrors().stream().map(err -> err.getField()
              .concat(ConstantsUtils.COLON_SEPARATOR).concat(err.getDefaultMessage()))
          .collect(Collectors.toList());
      return new GenericResponse(ConstantsUtils.VALIDATION_ERROR_MESSAGE, null, true, errors,
          HttpStatus.BAD_REQUEST).build();
    }
    User user = new User(userDto);
    User createdUser = null;
    user.setPassword(encoder.encode(user.getPassword()));
    try {
      createdUser = userService.save(user);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return new GenericResponse(ConstantsUtils.CREATED_MESSAGE, createdUser, HttpStatus.OK).build();
  }

  @Secured({"ROLE_ADMIN", "ROLE_SUPERVISOR"})
  @PutMapping("/update")
  public ResponseEntity<Map<String, Object>> update(@RequestBody UserDto userDto,
      BindingResult result) {
    User currentUser = null;
    if (result.hasErrors()) {
      List<String> errors = result
          .getFieldErrors().stream().map(err -> err.getField()
              .concat(ConstantsUtils.COLON_SEPARATOR).concat(err.getDefaultMessage()))
          .collect(Collectors.toList());
      return new GenericResponse(ConstantsUtils.VALIDATION_ERROR_MESSAGE, null, true, errors,
          HttpStatus.BAD_REQUEST).build();
    }
    GenericResponse genericResponseError = new GenericResponse(
        ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true, HttpStatus.INTERNAL_SERVER_ERROR);
    try {
      currentUser = userService.findById(userDto.getId());
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return genericResponseError.build();
    }

    if (currentUser == null) {
      return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, null, true,
          HttpStatus.NOT_FOUND).build();
    }
    currentUser.setName(userDto.getName());
    currentUser.setUsername(userDto.getUsername());
    currentUser.setEnabled(userDto.isEnabled());
    currentUser.setAuthority(userDto.getAuthority());

    try {
      userService.save(currentUser);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return genericResponseError.build();
    }
    return new GenericResponse(ConstantsUtils.UPDATED_MESSAGE, currentUser, HttpStatus.CREATED)
        .build();
  }

  @Secured({"ROLE_ADMIN", "ROLE_SUPERVISOR"})
  @PutMapping("/reset-password")
  public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody UserDto userDto,
      BindingResult result) {
    User currentUser = null;
    if (result.hasErrors()) {
      List<String> errors = result
          .getFieldErrors().stream().map(err -> err.getField()
              .concat(ConstantsUtils.COLON_SEPARATOR).concat(err.getDefaultMessage()))
          .collect(Collectors.toList());
      return new GenericResponse(ConstantsUtils.VALIDATION_ERROR_MESSAGE, null, true, errors,
          HttpStatus.BAD_REQUEST).build();
    }
    GenericResponse genericResponseError = new GenericResponse(
        ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true, HttpStatus.INTERNAL_SERVER_ERROR);
    try {
      currentUser = userService.findById(userDto.getId());
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return genericResponseError.build();
    }

    if (currentUser == null) {
      return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, null, true,
          HttpStatus.NOT_FOUND).build();
    }
    currentUser.setPassword(encoder.encode(userDto.getPassword()));
    try {
      userService.save(currentUser);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return genericResponseError.build();
    }
    return new GenericResponse(ConstantsUtils.SECRET_UPDATED_MESSAGE, currentUser,
        HttpStatus.CREATED).build();
  }

  @PutMapping("/change-password")
  public ResponseEntity<Map<String, Object>> changePassword(
      @RequestBody ChangePasswordDto changeData, BindingResult result) {
    User currentUser = null;
    if (result.hasErrors()) {
      List<String> errors = result
          .getFieldErrors().stream().map(err -> err.getField()
              .concat(ConstantsUtils.COLON_SEPARATOR).concat(err.getDefaultMessage()))
          .collect(Collectors.toList());
      return new GenericResponse(ConstantsUtils.VALIDATION_ERROR_MESSAGE, null, true, errors,
          HttpStatus.BAD_REQUEST).build();
    }
    GenericResponse genericResponseError = new GenericResponse(
        ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true, HttpStatus.INTERNAL_SERVER_ERROR);
    try {
      currentUser = userService.findById(changeData.getId());
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return genericResponseError.build();
    }

    if (currentUser == null) {
      return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, null, true,
          HttpStatus.NOT_FOUND).build();
    }

    if (!encoder.matches(changeData.getOldPassword(), currentUser.getPassword())) {
      return new GenericResponse("La antigua contraseña no es correcta.", null, true,
          HttpStatus.BAD_REQUEST).build();
    }
    currentUser.setPassword(encoder.encode(changeData.getNewPassword()));
    try {
      userService.save(currentUser);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return genericResponseError.build();
    }
    return new GenericResponse(ConstantsUtils.SECRET_UPDATED_MESSAGE, currentUser,
        HttpStatus.CREATED).build();
  }

  @Secured({"ROLE_ADMIN", "ROLE_SUPERVISOR"})
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
    User user = null;
    try {
      user = userService.delete(id);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    if (user == null) {
      return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, null, true,
          HttpStatus.NOT_FOUND).build();
    }
    return new GenericResponse(ConstantsUtils.DELETED_MESSAGE, user, HttpStatus.OK).build();
  }

  @PostMapping("/upload")
  public ResponseEntity<Map<String, Object>> upload(@RequestParam MultipartFile file,
      @RequestParam Integer id) {
    User user = null;
    if (!file.isEmpty()) {
      String filePath = properties.getImagesPath();
      GenericResponse genericResponseError = new GenericResponse(
          ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true, HttpStatus.INTERNAL_SERVER_ERROR);
      try {
        user = userService.findById(id);
      } catch (Exception e) {
        log.writeLog(e.getMessage());
        return genericResponseError.build();
      }

      if (user == null) {
        return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, null, true,
            HttpStatus.NOT_FOUND).build();
      }

      // Delete the previous image
      FilesUtils.deleteFile(user.getImage(), filePath);

      String fileName = "";
      try {
        fileName = FilesUtils.uploadFile(file, filePath);
      } catch (Exception e) {
        log.writeLog(e.getMessage());
        return new GenericResponse(ConstantsUtils.UPLOAD_FILE_ERROR_MESSAGE, null, true,
            HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      user.setImage(fileName);
      try {
        userService.save(user);
      } catch (Exception e) {
        log.writeLog(e.getMessage());
        return genericResponseError.build();
      }
    }
    return new GenericResponse(ConstantsUtils.UPLOADED_IMAGE_MESSAGE, user, HttpStatus.CREATED)
        .build();
  }

  @GetMapping("/image/{imageName:.+}")
  public ResponseEntity<Resource> showImage(@PathVariable String imageName) {
    Path filePath = Paths.get(properties.getImagesPath()).resolve(imageName).toAbsolutePath();
    Resource resource = null;
    try {
      resource = new UrlResource(filePath.toUri());
    } catch (MalformedURLException e) {
      log.writeLog(e.getMessage());
    }

    if (resource == null || !resource.exists() || !resource.isReadable()) {
      log.writeLog(ConstantsUtils.SHOW_IMAGE_ERROR_MESSAGE);
    }

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageName + "\"");
    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
  }

  @GetMapping("/exists/{username}")
  public ResponseEntity<Map<String, Object>> exists(@PathVariable String username) {
    boolean exists = false;
    String idUser = "";
    User user = null;
    try {
      user = userService.findByUsername(username);
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    if (user != null) {
      exists = true;
      idUser = user.getId().toString();
    }
    return new GenericResponse(idUser, exists, HttpStatus.OK).build();
  }

}
