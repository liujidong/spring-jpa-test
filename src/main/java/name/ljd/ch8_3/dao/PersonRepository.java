package name.ljd.ch8_3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import name.ljd.ch8_3.domain.Person;
@RepositoryRestResource(path="people")
public interface PersonRepository extends JpaRepository<Person, Long> {
	//Person findByNameStartsWith(String name);
	@RestResource(path="nameStartsWith",rel="nameStartsWith")
	Person findByNameStartsWith(@Param("name") String name);
	
}
