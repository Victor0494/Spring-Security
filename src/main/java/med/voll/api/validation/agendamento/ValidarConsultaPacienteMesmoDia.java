package med.voll.api.validation.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.validation.agendamento.ValidacoesConsulta;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidarConsultaPacienteMesmoDia implements ValidacoesConsulta {

    private final ConsultaRepository consultaRepository;
    @Override
    public void validarDadosConsulta(DadosAgendamentoConsultaDTO consultaDTO) {
        var primeiroHorario = consultaDTO.data().withHour(7);
        var ultimoHorario = consultaDTO.data().withHour(18);

        if(consultaRepository.existsByPacienteIdAndDataBetween(consultaDTO.pacienteId(), primeiroHorario, ultimoHorario)) {
            throw new ValidationdMedicalAppointmentException("Paciente possui duas consultas marcadas para o mesmo dia");
        }
    }
}
