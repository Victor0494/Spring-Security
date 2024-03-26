package med.voll.api.service;

import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AtualizaDadosMedicoDTO;
import med.voll.api.dto.DadosCadastroMedicoDTO;
import med.voll.api.dto.DadosDetalhamentoMedicoDTO;
import med.voll.api.dto.DadosListagemMedicoDTO;
import med.voll.api.entities.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl {

    private final MedicoRepository medicoRepository;


    public DadosDetalhamentoMedicoDTO createMedico(DadosCadastroMedicoDTO dados) {
        var medico  = new Medico(dados);
        medicoRepository.save(medico);
        
        return new DadosDetalhamentoMedicoDTO(medico);
    }


    public Page<DadosListagemMedicoDTO> medicoList(Pageable pageable) {
        return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListagemMedicoDTO::new);
    }

    public DadosDetalhamentoMedicoDTO updateMedico(AtualizaDadosMedicoDTO dto) {
        var medico = medicoRepository.getReferenceById(dto.id());
        medico.atualizaInfo(dto);

        return new DadosDetalhamentoMedicoDTO(medico);
    }

    public void deleteMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        
    }

    public DadosDetalhamentoMedicoDTO detailMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return new DadosDetalhamentoMedicoDTO(medico);
    }
}
