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
public class Hello {

	@GetMapping
	public String hello() {

		return "Autenticou ....";
	}

}
