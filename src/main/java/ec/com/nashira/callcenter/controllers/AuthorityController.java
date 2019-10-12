package ec.com.nashira.callcenter.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.com.nashira.callcenter.entities.Authority;
import ec.com.nashira.callcenter.logger.Logger;
import ec.com.nashira.callcenter.responseobjects.GenericResponse;
import ec.com.nashira.callcenter.services.AuthorityService;
import ec.com.nashira.callcenter.utils.ConstantsUtils;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

  @Autowired
  private AuthorityService authorityService;

  @Autowired
  private Logger log;

  @GetMapping("/index")
  public ResponseEntity<Map<String, Object>> index() {
    List<Authority> authorities = null;
    try {
      authorities = authorityService.findAll();
    } catch (Exception e) {
      log.writeLog(e.getMessage());
      return new GenericResponse(ConstantsUtils.DATABASE_ERROR_MESSAGE, null, true,
          HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return new GenericResponse("", authorities, HttpStatus.OK).build();
  }

}
