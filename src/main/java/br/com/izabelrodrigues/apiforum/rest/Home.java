/**
 *
 */
package br.com.izabelrodrigues.apiforum.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Izabel Rodrigues
 *
 */
@RestController
@RequestMapping("/")
public class Home {

	@GetMapping
	public String index() {

		return "<a href='http://localhost:8080/swagger-ui.html'>Acesse a pagina da documentação da api</a>";
	}

}
