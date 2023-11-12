package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/*  Essa anotação é usada para indicar que a classe à qual ela é aplicada é
    uma classe de teste de integração que deve ser executada com o suporte
    total do Spring*/
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    /*  MockMvc permite escrever testes de integração para aplicativos Spring MVC
    sem a necessidade de iniciar um servidor HTTP.
    Ele fornece um ambiente simulado para enviar solicitações HTTP e
    receber respostas, permitindo que você teste seus controladores
    sem a necessidade de execução completa do servidor.*/
    @Autowired
    private MockMvc mvc;

    /*@MockBean Spring Boot substituirá a instância real por um mock. Isso significa
    que, durante a execução do teste, as chamadas ao banco de dados não serão
    direcionadas ao banco de dados real, mas sim ao mock, que permite definir comportamentos
    específicos para essas chamadas durante o teste.
     */
    @MockBean
    private AgendaDeConsultas agendaDeConsultas;
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações estao invalidas")
    //ignora a autenticação de token de segurança criado. Ele simula um usuario logado
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informações estao validas")
    // @WithMockUser ignora a autenticação de token de segurança criado. Ele simula um usuario logado
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null,2l,5l, data);
        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);
        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new DadosAgendamentoConsulta(2l, 5l,data,especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }
}