package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AtualizaDadosPacienteDTO;
import med.voll.api.dto.DadosCadastroPacienteDTO;
import med.voll.api.dto.DadosDetalhamentoPacienteDTO;
import med.voll.api.dto.ListaPacienteDTO;
import med.voll.api.entities.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl {

    private final PacienteRepository pacienteRepository;

    public DadosDetalhamentoPacienteDTO createPaciente(DadosCadastroPacienteDTO pacienteDTO) {
        var paciente = new Paciente(pacienteDTO);
        pacienteRepository.save(paciente);

        return new DadosDetalhamentoPacienteDTO(paciente);
    }

    public Page<ListaPacienteDTO> getListPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(ListaPacienteDTO::new);
    }

    public DadosDetalhamentoPacienteDTO updatePaciente(AtualizaDadosPacienteDTO pacienteDTO) {
        var paciente = pacienteRepository.getReferenceById(pacienteDTO.id());
        paciente.atualizaPaciente(pacienteDTO);
        return new DadosDetalhamentoPacienteDTO(paciente);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public DadosDetalhamentoPacienteDTO getPacienteById(Long id) {
        var response = pacienteRepository.getReferenceById(id);
        return new DadosDetalhamentoPacienteDTO(response);
    }
}
