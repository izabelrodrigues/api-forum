/**
 *
 */
package br.com.izabelrodrigues.apiforum.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.izabelrodrigues.apiforum.domain.usuario.repository.UsuarioRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Usuario;

/**
 * @author Izabel Rodrigues
 *
 */

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> optional = repository.findByEmail(username);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
