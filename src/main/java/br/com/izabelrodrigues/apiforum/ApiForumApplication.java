package br.com.izabelrodrigues.apiforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 *
 * @author Izabel Rodrigues
 *
 */

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ApiForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiForumApplication.class, args);
	}

}
