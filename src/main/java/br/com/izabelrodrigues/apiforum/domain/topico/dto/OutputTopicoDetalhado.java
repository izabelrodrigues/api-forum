/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.topico.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import br.com.izabelrodrigues.apiforum.infra.model.enums.StatusTopico;
import lombok.Getter;

/**
 * @author Izabel Rodrigues
 *
 */

@Getter
public class OutputTopicoDetalhado extends OutputTopico {

	/**
	 *
	 */
	private static final long serialVersionUID = -5762609456274838361L;

	private String nomeAutor;
	private StatusTopico status;
	private List<OutputResposta> respostas = new ArrayList<>();

	public OutputTopicoDetalhado(Topico topico) {
		super(topico);
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		List<OutputResposta> list = topico.getRespostas().stream().map(OutputResposta::new).collect(Collectors.toList());
		this.respostas.addAll(list);
	}

}
