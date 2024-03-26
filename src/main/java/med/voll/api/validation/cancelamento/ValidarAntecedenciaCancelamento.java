package med.voll.api.validation.cancelamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DadosCancelamentoConsultaDTO;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.repository.ConsultaRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ValidarAntecedenciaCancelamento implements ValidacoesCancelamentoConsulta{

    private final ConsultaRepository consultaRepository;

    @Override
    public void validarDadosCancelamentoConsulta(DadosCancelamentoConsultaDTO consultaDTO) {
        var consulta = consultaRepository.getReferenceById(consultaDTO.consultaId());

        if(Duration.between(LocalDateTime.now(), consulta.getData()).toHoursPart() < 24 ) {
            throw new ValidationdMedicalAppointmentException("A consulta deve ser cancelada com 24 horas de antecedência");
        }
    }
}
