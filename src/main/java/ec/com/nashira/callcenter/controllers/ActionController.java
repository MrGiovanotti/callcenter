package ec.com.nashira.callcenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ec.com.nashira.callcenter.entities.Action;
import ec.com.nashira.callcenter.entities.dto.ActionDto;
import ec.com.nashira.callcenter.services.ActionService;

@RestController
@RequestMapping("/action")
public class ActionController {

  @Autowired
  private ActionService actionService;

  @GetMapping("/show/{id}")
  public Action show(@PathVariable Integer id) {
    return actionService.findById(id);
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Action create(@RequestBody ActionDto actionDto) {
    Action action = new Action(actionDto);
    return actionService.save(action);
  }

}
