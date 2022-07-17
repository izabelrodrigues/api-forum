package br.com.izabelrodrigues.apiforum.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.izabelrodrigues.apiforum.domain.usuario.repository.UsuarioRepository;
import br.com.izabelrodrigues.apiforum.infra.model.Perfil;
import br.com.izabelrodrigues.apiforum.infra.model.Usuario;

/**
 *
 * @author Izabel Rodrigues
 *
 */

class AutenticacaoServiceTest {

	@InjectMocks
	AutenticacaoService autenticacaoService;

	@Mock
	UsuarioRepository usuarioRepository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void validaSeUmUsuarioExistenteFoiRecuperado() {

		//Given
		Usuario usuario = new Usuario();
		usuario.setEmail("aluno@email.com");
		usuario.setId(1L);
		usuario.setNome("Aluno");
		usuario.setSenha("$2a$10$tKhsRSzpey9HCnYguqzAH.bW/n8hATydzkrTnAfn3ndL1ir5G4w3K");

		Perfil perfil = new Perfil();
		perfil.setId(1L);
		perfil.setNome("ROLE_ALUNO");
		List<Perfil> perfis = new ArrayList<>();
		perfis.add(perfil);

		usuario.setPerfis(perfis );

		Optional<Usuario> optionalUser = Optional.of(usuario);
		Mockito.when(usuarioRepository.findByEmail("aluno@email.com")).thenReturn(optionalUser);


		//When
		UserDetails userDetails = autenticacaoService.loadUserByUsername("aluno@email.com");

		//Then
		assertEquals("aluno@email.com", userDetails.getUsername());
		assertEquals("$2a$10$tKhsRSzpey9HCnYguqzAH.bW/n8hATydzkrTnAfn3ndL1ir5G4w3K",userDetails.getPassword());
		assertEquals(1,userDetails.getAuthorities().size());

		GrantedAuthority authoritie = userDetails.getAuthorities().iterator().next();
		assertEquals("ROLE_ALUNO",authoritie.getAuthority());

	}


	@Test
	void validaSeUmUsuarioInexistenteLancaUmaExcecaoAoTentarCarregarOsDados() {

		//Given
		Optional<Usuario> optionalUser = Optional.empty();
		Mockito.when(usuarioRepository.findByEmail("inexistente@email.com")).thenReturn(optionalUser);

		//When
		UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> autenticacaoService.loadUserByUsername("inexistente@email.com"));

		//Then
		assertEquals("Dados inválidos!", exception.getMessage());

	}

}
