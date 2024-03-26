package med.voll.api.validation.agendamento;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.validation.agendamento.ValidacoesConsulta;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidaMedicoAtivo implements ValidacoesConsulta {

    private final MedicoRepository medicoRepository;


    @Override
    public void validarDadosConsulta(DadosAgendamentoConsultaDTO consultaDTO) {
        if(consultaDTO.medicoId() == null) {
            return;
        }

        if(!medicoRepository.findAtivoById(consultaDTO.medicoId())) {
            throw new ValidationdMedicalAppointmentException("O medico informado não foi encontrado");
        }

    }
}
