/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.dto;

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

	private String titulo;
	private String mensagem;
	private String nomeCurso;

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
