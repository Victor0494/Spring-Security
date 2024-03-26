package med.voll.api.dto;

import org.springframework.validation.FieldError;

public record InvalidDateValidationDTO(String attribute, String message) {

    public InvalidDateValidationDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
