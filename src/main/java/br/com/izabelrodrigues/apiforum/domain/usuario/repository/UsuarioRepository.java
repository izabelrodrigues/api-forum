/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.usuario.repository;

import java.util.Optional;

import br.com.izabelrodrigues.apiforum.infra.model.Usuario;
import br.com.izabelrodrigues.apiforum.infra.repository.UsuarioRepositoryImpl;

/**
 * @author Izabel Rodrigues
 *
 */

public interface UsuarioRepository extends UsuarioRepositoryImpl {

	Optional<Usuario> findByEmail(String email);

}
