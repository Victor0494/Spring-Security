package med.voll.api.dto;

import med.voll.api.entities.Endereco;
import med.voll.api.entities.Paciente;

public record DadosDetalhamentoPacienteDTO(Long id, String nome, String email, String telefone, String documento, Endereco endereco) {

    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getDocumento(), paciente.getEndereco());
    }
}
