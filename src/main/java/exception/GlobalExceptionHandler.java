package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<String> handleServiceUnavailable(ResourceAccessException ex) {
		// Log the detailed exception here
		return new ResponseEntity<>("External API (MealDB) is currently unavailable or unreachable.",
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientError(HttpClientErrorException ex) {
		// Can extract more details if needed
		return new ResponseEntity<>("Error retrieving data from MealDB: " + ex.getStatusText(), ex.getStatusCode());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex) {
		// Log the detailed exception here
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}