/**
 *
 */
package br.com.izabelrodrigues.apiforum.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Izabel Rodrigues
 *
 */

@NoRepositoryBean
public interface ApiRepository<T> extends JpaRepository<T, Long> {

}
