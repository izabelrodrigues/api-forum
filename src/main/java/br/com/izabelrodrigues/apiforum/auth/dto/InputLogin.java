/**
 *
 */
package br.com.izabelrodrigues.apiforum.auth.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Izabel Rodrigues
 *
 */

@Getter
@Setter
public class InputLogin {

	private String email;
	private String senha;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
