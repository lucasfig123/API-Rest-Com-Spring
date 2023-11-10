package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component

public class ValidarHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{


    public void validar (DadosAgendamentoConsulta dados){

        var horaAgendamentoConsulta = dados.data();
        var horaAgora = LocalTime.now();
        if (Duration.between(horaAgora,horaAgendamentoConsulta).toMinutes() >= 30){
            throw new ValidacaoException("Consulta deve ser feita no mínimo com 30 minutos de antecedência");
        }
    }
}
