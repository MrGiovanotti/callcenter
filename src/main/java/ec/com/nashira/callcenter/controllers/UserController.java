package ec.com.nashira.callcenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.services.UserService;
import ec.com.nashira.callcenter.utils.ConstantsUtils;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final String PROPERTY_TO_ORDER_BY = "name";

	@Autowired
	private UserService userService;

	@GetMapping("/index/{page}")
	public Page<User> index(@PathVariable("page") int pageNumber) {
		return userService.findAll(PageRequest.of(pageNumber, ConstantsUtils.NUMBER_ITEMS_PER_PAGE,
				Sort.by(Sort.Direction.ASC, PROPERTY_TO_ORDER_BY)));
	}

}
