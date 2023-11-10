package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
/*Essa classe implementa a interface UserDetailsService, que é parte do Spring Security e é usada para carregar detalhes do usuário durante o processo de autenticação.
O método loadUserByUsername é chamado quando o AuthenticationManager precisa carregar os detalhes do usuário com base no nome de usuário (login) fornecido.*/
