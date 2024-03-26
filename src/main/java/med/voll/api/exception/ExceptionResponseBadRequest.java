package med.voll.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.InvalidDateValidationDTO;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
public class ExceptionResponseBadRequest extends ExceptionResponse {

    private List<InvalidDateValidationDTO> invalidDateValidation;

    public ExceptionResponseBadRequest(Integer httpStatusValue, String message, List<InvalidDateValidationDTO> invalidDateValidations) {
        super(httpStatusValue, message, List.of("Attributes with error"));
        this.invalidDateValidation = invalidDateValidations;
    }
}
