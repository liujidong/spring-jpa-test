package name.ljd.ch8_2.specs;

import static com.google.common.collect.Iterables.toArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
/**
 * 自动模糊查询，字符为like查询，其他为=查询
 * @author Lenovo
 *
 */
public class CustomerSpecs {
	//1：定义方法
	public static <T> Specification<T> byAuto(final EntityManager entityManager,final T example){
		final Class<T> type=(Class<T>)example.getClass();//2：获得当前实体对象的类型
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();//3：存储构造的查询条件
				//4：获得实体类的EntityType，可以从EntityType中获得实体类的属性
				EntityType<T> entity=entityManager.getMetamodel().entity(type);
				//5.对实体类的所有属性做循环
				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
					Object attrValue = getValue(example,attr);//6.获得属性值
					if(attrValue != null) {
						if(attr.getJavaType() == String.class) {//7
							if(!StringUtils.isEmpty(attrValue)) {//8
								//9构造当前属性like查询，并添加到条件列表
								predicates.add(cb.like(root.get(attribute(entity,attr.getName(), String.class)),pattern((String)attrValue)));
							}
						}else {
							//10构造当前属性equal查询，并添加到条件列表
							predicates.add(cb.equal(root.get(attribute(entity,attr.getName(),attrValue.getClass())), attrValue));
						}
					}
				}
				//11列表转换成Predicate
				return predicates.isEmpty()?cb.conjunction():cb.and(toArray(predicates,Predicate.class));
			}
			//12 通过反射得到实体属性对应的属性值
			private <T> Object getValue(T example,Attribute<T,?> attr) {
				return ReflectionUtils.getField((Field)attr.getJavaMember(), example);
			}
			/**
			 * 13 获得实体类当前属性的SingularAttribute，SingularAttribute包含实体类的某个单独属性
			 */
			private <E,T> SingularAttribute<T, E> attribute(EntityType<T> entity,String fieldName,Class<E> fieldClass){
				return entity.getDeclaredSingularAttribute(fieldName,fieldClass);
			}	
		};
	}
	/**
	 * 14 构造like查询
	 */
	static private String pattern(String str) {
		return "%"+str+"%";
	}
}
