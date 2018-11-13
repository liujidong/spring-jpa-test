package name.ljd.ch8_2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import name.ljd.ch8_2.dao.PersonRepository;
import name.ljd.ch8_2.domain.Person;

@RestController
public class DataController {
	@Autowired
	PersonRepository personRepository;
	/**
	 * 保存
	 * save支持批量保存；<S extends T> Iterable<S> save(Iterable<S> entities);
	 * 
	 * 删除
	 * 支持使用id删除对象、批量删除以及删除全部
	 * void delete(ID id);
	 * void delete(T entity);
	 * void delete(Iterable <? entends T> entities);
	 * void deleteAll();
	 */
	@RequestMapping("/save")
	public Person save(String name,String address,Integer age) {
		Person p = personRepository.save(new Person(null,name,age,address));
		return p;
	}
	/**
	 * 	测试findByAddress
	 * @param address
	 * @return
	 */
	@RequestMapping("/q1")
	public List<Person> q1(String address){
		List<Person> people = personRepository.findByAddress(address);
		return people;
	}
	/**
	 * 	测试findByNameAndAddress
	 * @param address
	 * @return
	 */
	@RequestMapping("/q2")
	public Person q2(String name,String address){
		Person people = personRepository.findByNameAndAddress(name, address);
		return people;
	}
	/**
	 * 	测试withNameAndAddressQuery
	 * @param name
	 * @param address
	 * @return
	 */
	@RequestMapping("/q3")
	public Person q3(String name,String address) {
		Person p = personRepository.withNameAndAddressQuery(name, address);
		return p;
	}
	@RequestMapping("/q4")
	public List<Person> q4(String name,String address) {
		List<Person> ps = personRepository.withNameAndAddressNamedQuery(name, address);
		return ps;
	}
	/**
	 * 	测试排序
	 * @return
	 */
	@RequestMapping("/sort")
	public List<Person> sort(){
		List<Person> people = personRepository.findAll(new Sort(Direction.ASC,"age"));
		return people;
	}
	/**
	 * 	测试分页
	 * @return
	 */
	@RequestMapping("/page")
	public Page<Person> page(){
		Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
		return pagePeople;
	}
}
