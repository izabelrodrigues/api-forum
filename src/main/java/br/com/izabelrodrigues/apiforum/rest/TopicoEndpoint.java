package br.com.izabelrodrigues.apiforum.rest;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.izabelrodrigues.apiforum.domain.curso.repository.CursoRepository;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.InputTopico;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.InputTopicoBase;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.OutputTopico;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.OutputTopicoDetalhado;
import br.com.izabelrodrigues.apiforum.domain.topico.repository.TopicRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 *
 * @author Izabel Rodrigues
 *
 * O uso de cache nesses endpoints é apenas a efeito de exemplo.
 * Mas na prática cache seria mais apropriado em tabelas de tipos e/ou outras entidades que raramente são modificadas
 *
 */

@Tag(name = TagsEndpoint.TOPICOS, description = "Operações relacionadas aos tópicos de um fórum")
@RestController
@RequestMapping("/topicos")
public class TopicoEndpoint {

	@Autowired
	private TopicRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	/**
	 * Para utilizar o recurso de paginação, precisa anotar a classe de start da aplicação com @EnableSpringDataWebSupport
	 *
	 * @param nomeCurso - Filtro por nome do curso
	 * @param paginacao
	 * @return
	 */
	@GetMapping
	@Cacheable(value = "listaDeTopicos")
	public Page<OutputTopico> listar(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		Page<Topico> topicos = null;
		if (null == nomeCurso) {
			topicos = topicoRepository.findAll(paginacao);
		} else {
			topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
		}

		return OutputTopico.converter(topicos);
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true) //Invalidar o cache para atualizar
	public ResponseEntity<OutputTopico> cadastrar(@RequestBody @Valid InputTopico input, UriComponentsBuilder uriBuilder) {

		Topico topico = input.converter(cursoRepository);
		topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); //Cria a uri já substituindo o id no {id}*

		return ResponseEntity.created(uri).body(new OutputTopico(topico));

	}

	@GetMapping("/{id}")
	public ResponseEntity<OutputTopicoDetalhado> buscarPorId(@PathVariable Long id) {

		Optional<Topico> optional = recuperaPorId(id);
		if (optional.isPresent()) {
			OutputTopicoDetalhado outputTopicoDetalhado = new OutputTopicoDetalhado(optional.get());
			return ResponseEntity.ok(outputTopicoDetalhado);
		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional // Ao finalizar o método, o spring efetuará o commit automático da transação
	public ResponseEntity<OutputTopico> atualizar(@PathVariable Long id, @RequestBody @Valid InputTopicoBase inputAtualizacao, UriComponentsBuilder uriBuilder) {

		Optional<Topico> optional = recuperaPorId(id);

		if (optional.isPresent()) {
			Topico topico = inputAtualizacao.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new OutputTopico(topico));
		}

		return ResponseEntity.notFound().build();

	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeTopicos", allEntries = true) //Invalidar o cache para atualizar
	public ResponseEntity remover(@PathVariable Long id) {

		Optional<Topico> optional = recuperaPorId(id);

		if (optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	private Optional<Topico> recuperaPorId(Long id) {
		return topicoRepository.findById(id);
	}

}
