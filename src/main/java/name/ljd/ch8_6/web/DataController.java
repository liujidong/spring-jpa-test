package name.ljd.ch8_6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import name.ljd.ch8_6.dao.PersonDao;
import name.ljd.ch8_6.domain.Person;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class DataController {

	@Autowired
	PersonDao personDao;
	
	@RequestMapping("/set")
	public void set() {
		Person person = new Person("1","wfy",32);
		personDao.save(person);
		personDao.stringRedisTemplateDemo();
	}
	@RequestMapping("/getStr")
	public String getStr() {
		return personDao.getString();
	}
	@RequestMapping(value="/getPerson", method=RequestMethod.GET)
	public Person getPerson() {
		return personDao.getPerson();
	}
	
}
