/**
 *
 */
package br.com.izabelrodrigues.apiforum.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Izabel Rodrigues
 *
 */

@Getter
@AllArgsConstructor
public class OutputToken {

	private String token;
	private String tipoAutenticacao;

}
