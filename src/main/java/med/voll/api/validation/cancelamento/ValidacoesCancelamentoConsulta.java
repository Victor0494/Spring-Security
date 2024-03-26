package med.voll.api.validation.cancelamento;

import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.DadosCancelamentoConsultaDTO;

public interface ValidacoesCancelamentoConsulta {

    void validarDadosCancelamentoConsulta(DadosCancelamentoConsultaDTO consultaDTO);
}
