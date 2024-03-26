package med.voll.api.validation.agendamento;

import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.validation.agendamento.ValidacoesConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamento implements ValidacoesConsulta {

    @Override
    public void validarDadosConsulta(DadosAgendamentoConsultaDTO consultaDTO) {
        var dataConsulta = consultaDTO.data();

        var sunDay = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpen = dataConsulta.getHour() < 7;
        var afterClose = dataConsulta.getHour() > 18;

        if(sunDay || beforeOpen || afterClose) {
            throw new ValidationdMedicalAppointmentException("Horário da consulta é inválido");
        }

    }
}
