package br.com.izabelrodrigues.apiforum.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.izabelrodrigues.apiforum.domain.curso.repository.CursoRepository;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.InputTopico;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.InputTopicoBase;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.OutputTopico;
import br.com.izabelrodrigues.apiforum.domain.topico.dto.OutputTopicoDetalhado;
import br.com.izabelrodrigues.apiforum.domain.topico.repository.TopicRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Topico;

/**
 *
 *
 * @author Izabel Rodrigues
 *
 */

@RestController
@RequestMapping("/topicos")
public class TopicoEndpoint {

	@Autowired
	private TopicRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<OutputTopico> listar(String nomeCurso) {
		List<Topico> topicos = null;
		if(null == nomeCurso) {
			topicos =  topicoRepository.findAll();
		} else {
			topicos =  topicoRepository.findByCursoNome(nomeCurso);
		}

		return OutputTopico.converter(topicos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<OutputTopico> cadastrar(@RequestBody @Valid InputTopico input, UriComponentsBuilder uriBuilder) {

		Topico topico = input.converter(cursoRepository);
		topicoRepository.save(topico);

		//Cria a uri já substituindo o id no {id}
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

		return ResponseEntity.created(uri).body(new OutputTopico(topico));

	}

	@GetMapping("/{id}")
	public OutputTopicoDetalhado buscarPorId(@PathVariable Long id) {

		Topico topico = topicoRepository.getReferenceById(id);
		return new OutputTopicoDetalhado(topico);

	}

	@PutMapping("/{id}")
	@Transactional //Ao finalizar o método, o spring efetuará o commit automático da transação
	public ResponseEntity<OutputTopico> atualizar(@PathVariable Long id, @RequestBody @Valid InputTopicoBase inputAtualizacao, UriComponentsBuilder uriBuilder){
		Topico topico = inputAtualizacao.atualizar(id, topicoRepository);
		return ResponseEntity.ok(new OutputTopico(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		topicoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}


}
