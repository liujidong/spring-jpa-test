package name.ljd.ch8_6.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import name.ljd.ch8_6.domain.Person;

public interface PersonRepository extends MongoRepository<Person, String> {
	
	Person findByName(String name);//1
	
	@Query("{'age':?0}")
	List<Person> withQueryFindByAge(Integer age);
	
}
