package name.ljd.ch8_5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import name.ljd.ch8_5.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	
}
