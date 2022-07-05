/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.curso.repository;

import br.com.izabelrodrigues.apiforum.infra.model.Curso;
import br.com.izabelrodrigues.apiforum.infra.repository.CursoRepositoryImpl;

/**
 * @author Izabel Rodrigues
 *
 */

public interface CursoRepository extends CursoRepositoryImpl {

	public Curso findByNome(String nomeCurso);
}
