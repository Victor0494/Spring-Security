package med.voll.api.validation.agendamento;

import med.voll.api.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.exception.ValidationdMedicalAppointmentException;
import med.voll.api.validation.agendamento.ValidacoesConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedencia implements ValidacoesConsulta {

    @Override
    public void validarDadosConsulta(DadosAgendamentoConsultaDTO consultaDTO) {
        if(Duration.between(LocalDateTime.now() ,consultaDTO.data()).toMinutes() < 30) {
            throw new ValidationdMedicalAppointmentException("Tempo da consulta deve ser com no minimo 30 min de antecedência");
        }
    }
}
