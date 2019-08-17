package ec.com.nashira.callcenter.responseobjects;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponse {

	private String message;
	private Object object;
	private boolean hasErrors;
	private HttpStatus httpStatus;

	private static final String MESSAGE = "message";
	private static final String OBJECT = "object";
	private static final String HAS_ERRORS = "hasErrors";

	public GenericResponse() {

	}

	public GenericResponse(String message, Object object, boolean hasErrors, HttpStatus httpStatus) {
		this.message = message;
		this.object = object;
		this.hasErrors = hasErrors;
		this.httpStatus = httpStatus;
	}

	public GenericResponse(String message, Object object, HttpStatus httpStatus) {
		this.message = message;
		this.object = object;
		hasErrors = false;
		this.httpStatus = httpStatus;
	}

	public ResponseEntity<Map<String, Object>> build() {
		Map<String, Object> response = new HashMap<>();
		response.put(MESSAGE, message);
		response.put(OBJECT, object);
		response.put(HAS_ERRORS, hasErrors);
		return new ResponseEntity<>(response, httpStatus);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
