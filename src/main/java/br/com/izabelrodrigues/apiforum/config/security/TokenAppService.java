/**
 *
 */
package br.com.izabelrodrigues.apiforum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.izabelrodrigues.apiforum.infra.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Izabel Rodrigues
 *
 */

@Service
public class TokenAppService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

		Date hoje = new Date();

		Date dataExpiracao = recuperaDataExpiracao(hoje);

		return Jwts.builder()
				.setIssuer("API Fórum")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}




	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	private Date recuperaDataExpiracao(Date hoje) {

		Long tempoExpiracao = Long.parseLong(expiration);
		long time = hoje.getTime();
		long tempoTotal = time + tempoExpiracao;

		return new Date(tempoTotal);
	}
}
