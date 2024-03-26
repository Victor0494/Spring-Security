package med.voll.api.dto;

import med.voll.api.entities.Paciente;

public record ListaPacienteDTO(Long id, String nome, String email, String telefone) {

    public ListaPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }
}
