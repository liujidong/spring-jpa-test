package name.ljd.ch8_5.service;

import name.ljd.ch8_5.domain.Person;

public interface DemoService {
	public Person save(Person person);
	
	public void remove(Long id);
	
	public Person findOne(Person person);
}
