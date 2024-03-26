package med.voll.api.dto;

import med.voll.api.constant.Especialidade;
import med.voll.api.entities.Endereco;
import med.voll.api.entities.Medico;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, String telefone ,Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
