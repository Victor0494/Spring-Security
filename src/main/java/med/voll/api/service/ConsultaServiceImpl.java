package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.DadosCancelamentoConsultaDTO;
import med.voll.api.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.entities.Consulta;
import med.voll.api.entities.Medico;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.validation.agendamento.ValidacoesConsulta;
import med.voll.api.validation.cancelamento.ValidacoesCancelamentoConsulta;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl {

    private final ConsultaRepository consultaRepository;

    private final PacienteRepository pacienteRepository;

    private final MedicoRepository medicoRepository;

    private final List<ValidacoesConsulta> validadores;

    private final List<ValidacoesCancelamentoConsulta> cancelamentoConsultas;

    public DadosDetalhamentoConsultaDTO agendarConsulta(DadosAgendamentoConsultaDTO consultaDTO) {
        if (!pacienteRepository.existsById(consultaDTO.pacienteId())) {
            throw new ValidationdMedicalAppointmentException("Id do paciente informado não existe");
        }

        if (consultaDTO.medicoId() != null && !medicoRepository.existsById(consultaDTO.medicoId())) {
            throw new ValidationdMedicalAppointmentException("Id do medico informado não existe");
        }

        var consulta = getNewConsulta(consultaDTO);

        validadores.forEach(v -> v.validarDadosConsulta(consultaDTO));
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsultaDTO(consulta);
    }

    private Consulta getNewConsulta(DadosAgendamentoConsultaDTO consultaDTO) {
        var paciente = pacienteRepository.getReferenceById(consultaDTO.pacienteId());
        var medico = getValidMedico(consultaDTO);
        if (Objects.isNull(medico)) {
            throw new ValidationdMedicalAppointmentException("Não há nem um médico disponível");
        }

        return new Consulta(consultaDTO, paciente, medico);
    }

    private Medico getValidMedico(DadosAgendamentoConsultaDTO consultaDTO) {
        if (consultaDTO.medicoId() != null) {
            return medicoRepository.getReferenceById(consultaDTO.medicoId());
        }

        if (consultaDTO.especialidade() == null) {
            throw new ValidationdMedicalAppointmentException("Especialidade é obrigatório quando não escolher o médico");
        }

        return medicoRepository.getMedicoByEspecialidadeAndDate(consultaDTO.especialidade(), consultaDTO.data());
    }

    public void cancelamentoConsulta(DadosCancelamentoConsultaDTO dados) {
        if (!consultaRepository.existsById(dados.consultaId())) {
            throw new ValidationdMedicalAppointmentException("Não foi possível encontrar a consulta");
        }

        cancelamentoConsultas.forEach(v -> v.validarDadosCancelamentoConsulta(dados));

        var consulta = consultaRepository.getReferenceById(dados.consultaId());

        consulta.setMotivoCancelamento(dados.motivoCancelamento());
    }
}
