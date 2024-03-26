package med.voll.api.controller;

import med.voll.api.constant.Especialidade;
import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.service.ConsultaServiceImpl;
import org.junit.jupiter.api.Assertions;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc; //O mockMvc simula uma requisição ao meu controller mas precisa também da tag na classe @AutoConfigureMockMvc

    @MockBean
    private ConsultaServiceImpl consultaService;

    @Autowired
    private JacksonTester<DadosAgendamentoConsultaDTO> dadosAgendamentoConsultaDTOJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsultaDTO> detalhamentoConsultaDTOJacksonTester;

    @Test
    @DisplayName("Validar retorno bad request ao enviar dados inválidos")
    @WithMockUser
        // Essa anotation diz para o Spring simular a requisição com um usuário válido
    void agendarConsulta() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Validar retorno ok ao enviar dados validos")
    @WithMockUser
    void agendarConsultaComSucesso() throws Exception {
        //Arrange
        var data = LocalDateTime.now().plusDays(2L);
        var jsonAgendatamento = new DadosAgendamentoConsultaDTO(2L, 1L, data, Especialidade.CARDIOLOGIA);
        var dadosDetalhamentoConsultaDTO = new DadosDetalhamentoConsultaDTO(null, 2L, 1L, data);
        var json = detalhamentoConsultaDTOJacksonTester.write(dadosDetalhamentoConsultaDTO).getJson();
        when(consultaService.agendarConsulta(jsonAgendatamento)).thenReturn(dadosDetalhamentoConsultaDTO);

        //Act
        var response = mvc.perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaDTOJacksonTester.write(jsonAgendatamento).getJson()))
                                .andReturn().getResponse();
        //Assert

        assertThat(response.getContentAsString()).isEqualTo(json);
    }
}