package med.voll.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.constant.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaDTO(Long medicoId,
                                          @NotNull
                                          Long pacienteId,

                                          @NotNull
                                          @Future // Valida se a data é no futuro
                                          LocalDateTime data,

                                          Especialidade especialidade) {
}
