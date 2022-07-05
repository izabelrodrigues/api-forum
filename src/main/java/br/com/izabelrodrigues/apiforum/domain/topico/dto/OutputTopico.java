/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.topico.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import lombok.Getter;

/**
 * @author Izabel Rodrigues
 *
 */

@Getter
public class OutputTopico {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public OutputTopico(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public static List<OutputTopico> converter (List<Topico> topicos){

		return topicos.stream().map(OutputTopico::new).collect(Collectors.toList());

	}

}
