/**
 *
 */
package br.com.izabelrodrigues.apiforum.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.izabelrodrigues.apiforum.auth.dto.InputLogin;
import br.com.izabelrodrigues.apiforum.auth.dto.OutputToken;
import br.com.izabelrodrigues.apiforum.config.security.TokenAppService;
import br.com.izabelrodrigues.apiforum.rest.TagsEndpoint;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Izabel Rodrigues
 *
 */

@Tag(name = TagsEndpoint.AUTENTICACAO, description = "Endpoint com operação para realizar autenticação")
@RestController
@RequestMapping("/auth")
public class AutenticacaoEndpoint {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenAppService tokenService;

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity autenticar(@RequestBody @Valid InputLogin inputLogin) {

		UsernamePasswordAuthenticationToken dadosLogin = inputLogin.converter();

		try {

			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new OutputToken(token, "Bearer"));

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
