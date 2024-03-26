package med.voll.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExceptionResponse {

    private Integer code;
    private String message;
    private List<String> details;

}
