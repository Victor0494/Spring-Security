package med.voll.api.validation.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.validation.agendamento.ValidacoesConsulta;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidarMedicoComOutraConsulta implements ValidacoesConsulta {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validarDadosConsulta(DadosAgendamentoConsultaDTO consultaDTO) {

        if(consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(consultaDTO.medicoId(), consultaDTO.data())) {
            throw new ValidationdMedicalAppointmentException("Médico já possui uma consulta agendada");
        }

    }
}
