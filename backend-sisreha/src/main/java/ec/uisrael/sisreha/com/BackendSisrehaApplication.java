package ec.uisrael.sisreha.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ec.uisrael.sisreha.com.dao.impl.GenericDao;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = GenericDao.class)
public class BackendSisrehaApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(BackendSisrehaApplication.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackendSisrehaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 2; i++) {
			String bCryptPasswordEncoder = passwordEncoder.encode("admin");
			LOG.info(bCryptPasswordEncoder);
		}
	}

}
