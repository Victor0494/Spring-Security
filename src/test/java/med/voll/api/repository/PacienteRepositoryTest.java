package med.voll.api.repository;

import med.voll.api.dto.DadosCadastroPacienteDTO;
import med.voll.api.dto.DadosEnderecoDTO;
import med.voll.api.entities.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("test")
class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Encontrar um paciente ativo pelo id")
    void findByAtivoById() {

        var paciente = cadastrarPaciente("victor", "teste@test.com", "02588786064", true);

        var pacienteStatus = pacienteRepository.findByAtivoById(paciente.getId());

        assertThat(pacienteStatus).isEqualTo(paciente.getAtivo());
    }

    @Test
    @DisplayName("Retornar false quando buscar um usuário pelo status e pelo id")
    void notFindByAtivoById() {

        var paciente = cadastrarPaciente("victor", "teste@test.com", "02588786064", false);

        var pacienteStatus = pacienteRepository.findByAtivoById(paciente.getId());

        assertThat(pacienteStatus).isEqualTo(paciente.getAtivo());
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf, Boolean status) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        if(status)
        paciente.setAtivo(status);
        testEntityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastroPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEnderecoDTO dadosEndereco() {
        return new DadosEnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}