/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.topico.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.izabelrodrigues.apiforum.domain.curso.repository.CursoRepository;
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
public class InputTopico extends InputTopicoBase {

	@NotNull @NotEmpty
	private String nomeCurso;

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
