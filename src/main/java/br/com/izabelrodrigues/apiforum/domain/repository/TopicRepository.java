package br.com.izabelrodrigues.apiforum.domain.repository;

import java.util.List;

import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import br.com.izabelrodrigues.apiforum.infra.repository.TopicoRepositoryImpl;

public interface TopicRepository extends TopicoRepositoryImpl {

	public List<Topico> findByCursoNome(String nomeCurso);
}
