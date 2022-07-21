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
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author Izabel Rodrigues
 *
 */

@Tag(name = TagsEndpoint.AUTENTICACAO, description = "Endpoint com operação para realizar autenticação")
@RestController
@RequestMapping("/auth")
public class AutenticacaoEndpoint {

	Counter authUserSuccess;
	Counter authUserErrors;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenAppService tokenService;

	public AutenticacaoEndpoint(MeterRegistry meterRegistry) {

		authUserSuccess = Counter.builder("auth_user_success")
				.description("Usuários autenticados")
				.register(meterRegistry);

		authUserErrors = Counter.builder("auth_user_error")
				.description("Erros de login")
				.register(meterRegistry);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity autenticar(@RequestBody @Valid InputLogin inputLogin) {

		UsernamePasswordAuthenticationToken dadosLogin = inputLogin.converter();

		try {

			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			authUserSuccess.increment();
			return ResponseEntity.ok(new OutputToken(token, "Bearer"));

		} catch (AuthenticationException e) {
			authUserErrors.increment();
			return ResponseEntity.badRequest().build();
		}

	}
}
