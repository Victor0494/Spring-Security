package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.constant.MotivoCancelamento;

public record DadosCancelamentoConsultaDTO(@NotNull Long consultaId,
                                           @NotNull MotivoCancelamento motivoCancelamento) {
}
