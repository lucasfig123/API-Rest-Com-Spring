package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);
}
/*Esta é uma interface que estende o JpaRepository e é usada para acessar os dados do usuário no banco de dados.
Define um método findByLogin que é usado pelo AutenticacaoService para buscar um usuário pelo nome de usuário (login).*/
