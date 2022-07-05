package br.com.izabelrodrigues.apiforum.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Izabel Rodrigues
 *
 */

@Getter
@Setter
@AllArgsConstructor
public class InputError {

	 private String campo;
     private String erro;

}
