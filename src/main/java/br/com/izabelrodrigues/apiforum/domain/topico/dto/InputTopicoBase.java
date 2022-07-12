/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.topico.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.izabelrodrigues.apiforum.domain.topico.repository.TopicRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Izabel Rodrigues
 *
 */

@Getter
@Setter
public class InputTopicoBase {

	@NotNull @NotEmpty @Length(min = 5, max = 255)
	protected String titulo;

	@NotNull @NotEmpty @Length(min = 10)
	protected String mensagem;

	public Topico atualizar(Long id, TopicRepository topicoRepository) {
		Topico topico = topicoRepository.getReferenceById(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
}
