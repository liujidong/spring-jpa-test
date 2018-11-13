package name.ljd.ch8_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import name.ljd.ch8_2.dao.PersonRepository;
import name.ljd.ch8_2.support.CustomRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass=
CustomRepositoryFactoryBean.class)
public class JpaTestApplication {
	//@Autowired
	//PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaTestApplication.class, args);
	}
}
