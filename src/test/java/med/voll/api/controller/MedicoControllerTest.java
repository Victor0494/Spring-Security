package med.voll.api.controller;

import med.voll.api.constant.Especialidade;
import med.voll.api.dto.DadosCadastroMedicoDTO;
import med.voll.api.dto.DadosDetalhamentoMedicoDTO;
import med.voll.api.dto.DadosEnderecoDTO;
import med.voll.api.entities.Endereco;
import med.voll.api.service.MedicoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroMedicoDTO> cadastroMedicoDTOJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedicoDTO> detalhamentoMedicoDTOJacksonTester;
    @MockBean
    private MedicoServiceImpl medicoService;

    @Test
    @DisplayName("Criar médico com sucesso")
    @WithMockUser
    void createMedico() throws Exception {
        var endereco = new Endereco("rua", "bairro", "cep", "numero", "complemento", "cidade", "uf");
        var enderecoDTO = new DadosEnderecoDTO("rua", "bairro", "99530000", "cidade", "uf", "complemento", "numero");
        var medicoDetalhamento = new DadosDetalhamentoMedicoDTO(1L, "Medico", "medico@medico.com", "123456", "55996847", Especialidade.CARDIOLOGIA, endereco);
        var cadastro = new DadosCadastroMedicoDTO("Medico", "teste@test.com", "549968", "123456", Especialidade.CARDIOLOGIA, enderecoDTO);
        when(medicoService.createMedico(cadastro)).thenReturn(medicoDetalhamento);
        var json = detalhamentoMedicoDTOJacksonTester.write(medicoDetalhamento).getJson();

        var response = mvc.perform(
                post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cadastroMedicoDTOJacksonTester.write(cadastro).getJson())

        ).andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertThat(response.getContentAsString()).isEqualTo(json);

    }

    @Test
    @DisplayName("Criar médico com erro")
    @WithMockUser
    void createMedicoComErro() throws Exception {
        var response = mvc.perform(
                post("/medicos")).andExpect(status().isBadRequest());
    }
}