package com.RentalBuilding.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = ExistedException.class)
	   public ResponseEntity<Object> exception(ExistedException exception) {
	      return new ResponseEntity<>("Object already exists", HttpStatus.BAD_REQUEST);
	   }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = NotExistedException.class)
	   public ResponseEntity<Object> exception(NotExistedException exception) {
	      return new ResponseEntity<>("Object not exists", HttpStatus.BAD_REQUEST);
	   }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = FieldRequiredException.class)
	   public ResponseEntity<Object> exception(FieldRequiredException exception) {
	      return new ResponseEntity<>("Missing some fields request", HttpStatus.BAD_REQUEST);
	   }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = ReferenceException.class)
	   public ResponseEntity<Object> exception(ReferenceException exception) {
	      return new ResponseEntity<>("Object cannot be deleted. Because some other objects is referencing its primary key", HttpStatus.BAD_REQUEST);
	   }
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidPageException.class)
	   public ResponseEntity<Object> exception(InvalidPageException exception) {
	      return new ResponseEntity<>("Invalid page input", HttpStatus.BAD_REQUEST);
	   }
}
