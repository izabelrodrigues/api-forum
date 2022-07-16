/**
 *
 */
package br.com.izabelrodrigues.apiforum.domain.curso.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import br.com.izabelrodrigues.apiforum.infra.model.Curso;

/**
 * @author Izabel Rodrigues
 *
 */


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CursoRepositoryTest {

	private static final String CURSO_02 = "CURSO 02";

	private static final String CURSO_01 = "CURSO 01";

	private static final String CURSO_JAVA_11 = "JAVA 11";

	private static final String CATEGORIA_PROGRAMACAO = "Programação";

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private TestEntityManager em;

	@Mock
	private Pageable paginacao;

	@Test
	void deveriaRecuperarUmCursoQuandoAPesquisaForPeloNomeDeUmCursoExistente() {

		String nomeCurso = CURSO_JAVA_11;
		mockInsertCursoDB(nomeCurso);

		Curso cursoEncontrado = cursoRepository.findByNome(nomeCurso);

		assertEquals(nomeCurso, cursoEncontrado.getNome());

		Curso cursoNaoEncontrado = cursoRepository.findByNome("HTML 5");
		assertNull(cursoNaoEncontrado);

	}


	@Test
	void naoDeveriaRecuperarUmCursoQuandoAPesquisaForPeloNomeDeUmCursoInexistente() {

		String nomeCurso = CURSO_JAVA_11;

		Curso cursoNaoEncontrado = cursoRepository.findByNome(nomeCurso);

		assertNull(cursoNaoEncontrado);

	}

	@Test
	void verificaBuscaTodosCursos() {

		mockInsertCursoDB(CURSO_01);
		mockInsertCursoDB(CURSO_02);

		Page<Curso> lista = cursoRepository.findAll(paginacao);

		assertEquals(2, lista.getContent().size());
		assertEquals(1,lista.getTotalPages());

		Curso curso01 = lista.getContent().get(0);
		assertEquals(1, curso01.getId());
		assertEquals(CURSO_01, curso01.getNome());
		assertEquals(CATEGORIA_PROGRAMACAO, curso01.getCategoria());

		Curso curso02 = lista.getContent().get(1);
		assertEquals(2, curso02.getId());
		assertEquals(CURSO_02, curso02.getNome());
		assertEquals(CATEGORIA_PROGRAMACAO, curso02.getCategoria());

	}

	private void mockInsertCursoDB(String nomeCurso) {
		Curso curso = new Curso();
		curso.setNome(nomeCurso);
		curso.setCategoria(CATEGORIA_PROGRAMACAO);
		em.persist(curso);
	}

}
