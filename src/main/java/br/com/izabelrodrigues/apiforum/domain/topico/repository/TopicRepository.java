package br.com.izabelrodrigues.apiforum.domain.topico.repository;

import java.util.List;

import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import br.com.izabelrodrigues.apiforum.infra.repository.TopicoRepositoryImpl;

/**
 *
 * @author Izabel Rodrigues
 *
 */

public interface TopicRepository extends TopicoRepositoryImpl {

	public List<Topico> findByCursoNome(String nomeCurso);
}
