package med.voll.api.validation.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.validation.agendamento.ValidacoesConsulta;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidarPacienteAtivo implements ValidacoesConsulta {

    private final PacienteRepository pacienteRepository;

    @Override
    public void validarDadosConsulta(DadosAgendamentoConsultaDTO consultaDTO) {

        if(!pacienteRepository.findByAtivoById(consultaDTO.pacienteId())) {
            throw new ValidationdMedicalAppointmentException("Paciente não esta ativo");
        }
    }
}
