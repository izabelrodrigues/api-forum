/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.topico.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.izabelrodrigues.apiforum.infra.model.Topico;
import lombok.Getter;

/**
 * @author Izabel Rodrigues
 *
 */

@Getter
public class OutputTopico implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -864540616383926714L;
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

	public static Page<OutputTopico> converter (Page<Topico> topicos){

		return topicos.map(OutputTopico::new);

	}

}
