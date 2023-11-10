package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {


    /*"AuthenticationManager" -> Classe responsável por disparar o processo de autenticação,
    porém ele por si só não consegue injetar esse atributo na classe, por isso é necessário configurar a classe
    "SecurityConfigurations" no método "authenticationManager" para retornar
    */
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efeturaLogin(@RequestBody @Valid DadosAutenticacao dados){

        // Cria a conexão/autorização do token para o usuário
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        //devolve um objeto que representa o usuário autenticado no sistema
        var authentication = manager.authenticate(authenticationToken);


        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}

        /*Essa classe é um controlador Spring que lida com as solicitações de autenticação.
        O método efetuaLogin é acessado quando um cliente faz uma solicitação POST para "/login" com os dados de autenticação (login e senha) no corpo da solicitação.
        Ele usa o AuthenticationManager para autenticar o usuário com base nos dados fornecidos.
        Em seguida, gera um token JWT usando o TokenService e o retorna como resposta.

        Em resumo, o fluxo de autenticação funciona da seguinte forma:

        O cliente faz uma solicitação POST para "/login" com credenciais (login e senha).
        O AutenticacaoController usa o AuthenticationManager para autenticar o usuário.
        O AuthenticationManager chama o AutenticacaoService, que carrega os detalhes do usuário do banco de dados com base no login.
        Se a autenticação for bem-sucedida, um token JWT é gerado pelo TokenService.
        O token JWT é retornado como resposta ao cliente.
        Essa é uma implementação típica de autenticação em um aplicativo Spring Boot, usando o Spring Security e tokens JWT para fornecer autenticação segura.
        */

