package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPacienteDTO(@NotBlank
                                       String nome,
                                       @NotBlank
                                       @Email
                                       String email,

                                       @NotBlank
                                       String telefone,

                                       @NotBlank
                                       String documento,

                                       @NotNull @Valid
                                       DadosEnderecoDTO endereco) {
}
