package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
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
    "SecurityConfigurations" para retornar
    */

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efeturaLogin(@RequestBody @Valid DadosAutenticacao dados){

        // Cria o token para o usuário
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        //devolve um objeto que representa o usuário autenticado no sistema
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
