/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.dto;

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
public class TopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public static List<TopicoDto> converter (List<Topico> topicos){

		return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());

	}

}
