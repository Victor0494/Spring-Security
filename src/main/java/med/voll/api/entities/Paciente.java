package med.voll.api.entities;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.AtualizaDadosPacienteDTO;
import med.voll.api.dto.DadosCadastroPacienteDTO;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String documento;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.nome();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.telefone();
        this.documento = pacienteDTO.documento();
        this.ativo = true;
        this.endereco = new Endereco(pacienteDTO.endereco());
    }

    public void atualizaPaciente(AtualizaDadosPacienteDTO pacienteDTO) {
        if(pacienteDTO.nome() != null) {
            this.nome = pacienteDTO.nome();
        }

        if(pacienteDTO.email() != null) {
            this.email = pacienteDTO.email();
        }

        if(pacienteDTO.telefone() != null) {
            this.telefone = pacienteDTO.telefone();
        }
    }
}
