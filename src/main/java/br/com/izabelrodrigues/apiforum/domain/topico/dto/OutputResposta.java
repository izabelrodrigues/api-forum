/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.topico.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.izabelrodrigues.apiforum.infra.model.Resposta;
import lombok.Getter;

/**
 * @author Izabel Rodrigues
 *
 */

@Getter
public class OutputResposta implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8131637862552324004L;
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;

	public OutputResposta(Resposta resposta) {

		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();

	}

}
