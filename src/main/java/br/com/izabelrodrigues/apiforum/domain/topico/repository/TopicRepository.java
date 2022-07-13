package br.com.izabelrodrigues.apiforum.domain.topico.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import br.com.izabelrodrigues.apiforum.infra.repository.TopicoRepositoryImpl;

/**
 *
 * @author Izabel Rodrigues
 *
 */

public interface TopicRepository extends TopicoRepositoryImpl {

	public Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);
}
