package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    /* A anotação @Bean serve para exportar uma classe para o Spring, fazendo com que ele consiga
    * carregá-la  e realize a sua injeção de depêndencia em outras classes*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        /* a classe AuthenticationConfiguration tem um método
           getAuthenticationManager() que sabe crirar o objeto
           AuthenticationManager */
        return configuration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
/*Esta é uma classe de configuração do Spring Security.
O método securityFilterChain configura as regras de segurança, desabilitando o CSRF e configurando a política de criação de sessão para STATELESS, o que significa que as sessões não serão mantidas no servidor.
O método authenticationManager cria e configura o AuthenticationManager para ser usado pelo Spring Security. O AuthenticationManager é usado para autenticar os usuários.
O método passwordEncoder configura o codificador de senhas. No seu caso, você está usando o BCrypt para codificar senhas.*/
