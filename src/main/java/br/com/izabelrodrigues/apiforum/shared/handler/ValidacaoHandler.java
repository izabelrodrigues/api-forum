/**
 *
 */
package br.com.izabelrodrigues.apiforum.shared.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.izabelrodrigues.apiforum.shared.dto.InputError;

/**
 * @author Izabel Rodrigues
 *
 */

@RestControllerAdvice
public class ValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<InputError> handle(MethodArgumentNotValidException exception) {

		List<InputError> erros = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			InputError erro = new InputError(e.getField(), mensagem);
			erros.add(erro);
		});

		return erros;

	}
}
