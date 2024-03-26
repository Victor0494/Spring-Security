package med.voll.api.dto;

import med.voll.api.constant.Especialidade;
import med.voll.api.entities.Medico;

public record DadosListagemMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
