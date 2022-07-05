/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.izabelrodrigues.apiforum.domain.repository.CursoRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Curso;
import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Izabel Rodrigues
 *
 */
@Getter
@Setter
public class InputTopico {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;

	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;

	@NotNull @NotEmpty
	private String nomeCurso;

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
