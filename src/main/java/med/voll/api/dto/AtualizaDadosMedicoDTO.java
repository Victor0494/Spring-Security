package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizaDadosMedicoDTO(
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco) {
}
