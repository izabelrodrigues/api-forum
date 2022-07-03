package br.com.izabelrodrigues.apiforum.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.izabelrodrigues.apiforum.domain.dto.TopicoDto;
import br.com.izabelrodrigues.apiforum.domain.repository.TopicRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Topico;

@RestController
public class TopicoEndpoint {

	@Autowired
	private TopicRepository topicoRepository;

	@GetMapping("/topicos")
	public List<TopicoDto> lista(String nomeCurso) {
		List<Topico> topicos = null;
		if(null == nomeCurso) {
			topicos =  topicoRepository.findAll();
		} else {
			topicos =  topicoRepository.findByCursoNome(nomeCurso);
		}

		return TopicoDto.converter(topicos);
	}



}
