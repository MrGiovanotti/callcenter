package ec.com.nashira.callcenter.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.logger.Logger;
import ec.com.nashira.callcenter.responseobjects.GenericResponse;
import ec.com.nashira.callcenter.services.UserService;
import ec.com.nashira.callcenter.utils.ConstantsUtils;

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

	@GetMapping("/index/{page}")
	public Page<User> index(@PathVariable("page") int pageNumber) {
		return userService.findAll(PageRequest.of(pageNumber, ConstantsUtils.NUMBER_ITEMS_PER_PAGE,
				Sort.by(Sort.Direction.ASC, PROPERTY_TO_ORDER_BY)));
	}

	@GetMapping("/show/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		User user = null;
		try {
			user = userService.findById(id);
		} catch (Exception e) {
			log.writeLog(e.getMessage());
			return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
					HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		if (user == null) {
			return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, user, true, HttpStatus.NOT_FOUND)
					.build();
		}
		return new GenericResponse(ConstantsUtils.FOUND_RESOURCE_MESSAGE, user, HttpStatus.OK).build();
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
		User createdUser = null;
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> err.getField().concat(ConstantsUtils.COLON_SEPARATOR).concat(err.getDefaultMessage()))
					.collect(Collectors.toList());
			return new GenericResponse(ConstantsUtils.VALIDATION_ERROR_MESSAGE, null, true, errors,
					HttpStatus.BAD_REQUEST).build();
		}
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

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
		User currentUser = null;
		User updatedUser = null;
		GenericResponse genericResponseError = new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
				HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			currentUser = userService.findById(id);
		} catch (Exception e) {
			log.writeLog(e.getMessage());
			return genericResponseError.build();
		}

		if (currentUser == null) {
			return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, null, true, HttpStatus.NOT_FOUND)
					.build();
		}
		currentUser.setName(user.getName());
		currentUser.setUsername(user.getUsername());
		currentUser.setPassword(encoder.encode(user.getPassword()));
		currentUser.setEnabled(user.isEnabled());

		try {
			updatedUser = userService.save(currentUser);
		} catch (Exception e) {
			log.writeLog(e.getMessage());
			return genericResponseError.build();
		}
		return new GenericResponse(ConstantsUtils.UPDATED_MESSAGE, updatedUser, HttpStatus.CREATED).build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		User user = null;
		try {
			user = userService.delete(id);
		} catch (Exception e) {
			log.writeLog(e.getMessage());
			return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
					HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		if (user == null) {
			return new GenericResponse(ConstantsUtils.NOT_FOUND_RESOURCE_MESSAGE, null, true, HttpStatus.NOT_FOUND)
					.build();
		}
		return new GenericResponse(ConstantsUtils.DELETED_MESSAGE, user, HttpStatus.OK).build();
	}

}
