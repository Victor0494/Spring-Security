package med.voll.api.validation.agendamento;

import med.voll.api.dto.DadosAgendamentoConsultaDTO;

public interface ValidacoesConsulta {

    void validarDadosConsulta(DadosAgendamentoConsultaDTO consultaDTO);
}
