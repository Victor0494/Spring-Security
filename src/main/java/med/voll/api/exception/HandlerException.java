package med.voll.api.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.dto.InvalidDateValidationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlerUserNotFound() {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), "Entity not found", List.of("Unable to find the entity in the data base"));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errorList = ex.getFieldErrors();

        var invalidDateValidations = errorList.stream().map(InvalidDateValidationDTO::new).toList();

        ExceptionResponseBadRequest response = new ExceptionResponseBadRequest(HttpStatus.BAD_REQUEST.value(), "Invalid field", invalidDateValidations);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ValidationdMedicalAppointmentException.class)
    public ResponseEntity handlerValidationdMedicalAppointmentException(ValidationdMedicalAppointmentException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), List.of(ex.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}
