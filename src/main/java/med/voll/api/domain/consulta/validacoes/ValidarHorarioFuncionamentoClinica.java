package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        var dataAgendamentoConsulta = dados.data();
        var domingo = dataAgendamentoConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var dataHoraAbertura = dataAgendamentoConsulta.getHour() < 7;
        var dataHoraEncerramento = dataAgendamentoConsulta.getHour() > 18;

        if(domingo || dataHoraAbertura || dataHoraEncerramento){
            throw new ValidacaoException("Consulta fora do horário de atendimento da clínica");
        }
    }
}
