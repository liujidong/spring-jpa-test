package name.ljd.ch8_2.support;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
/**
 * 继承JpaRepository和Specification
 * @author Lenovo
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomRepository<T,ID extends Serializable> extends 
JpaRepository<T, ID>,JpaSpecificationExecutor<T>{
	Page<T> findByAuto(T example,Pageable pageable);
}
