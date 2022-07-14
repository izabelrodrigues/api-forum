/**
 *
 */
package br.com.izabelrodrigues.apiforum.infra.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Izabel Rodrigues
 *
 */

@Entity
@Getter
@Setter
public class Perfil implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = -8150949810726219358L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
}
