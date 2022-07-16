/**
 *
 */
package br.com.izabelrodrigues.apiforum.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.izabelrodrigues.apiforum.domain.usuario.repository.UsuarioRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Usuario;
import br.com.izabelrodrigues.apiforum.shared.exception.AutenticacaoException;
import lombok.AllArgsConstructor;

/**
 *
 * TokenAppService e UsuarioRepository estão sendo injetados pelo construtor devido à classe
 * AutenticacaoViaTokenFilter ser do tipo filtro e não do tipo de um Bean gerenciado pelo Spring
 *
 * @author Izabel Rodrigues
 *
 */

@AllArgsConstructor
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private static final String TIPO_AUTORIZACAO = "Bearer ";
	private static final String HEADER_AUTORIZACAO = "Authorization";

	private TokenAppService tokenService;

	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);

		if (tokenService.isTokenValido(token)) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);

	}

	private void autenticarCliente(String token) throws AutenticacaoException {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
		Usuario usuario = null;
		if(!optional.isPresent()) {
			throw new AutenticacaoException();
		}
		usuario = optional.get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_AUTORIZACAO);

		if (ObjectUtils.isEmpty(token) || !token.startsWith(TIPO_AUTORIZACAO)) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
