/**
 *
 */
package br.com.izabelrodrigues.apiforum.shared.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izabelrodrigues.apiforum.shared.exception.AutenticacaoException;

/**
 * @author Izabel Rodrigues
 *
 */

@RestControllerAdvice
public class AutenticacaoHandler {

	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AutenticacaoException.class)
	public String handle(AutenticacaoException exception) {
		return exception.getMessage();

	}
}
