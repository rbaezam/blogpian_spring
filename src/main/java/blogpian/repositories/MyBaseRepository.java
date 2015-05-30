package blogpian.repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by rodolfo on 5/26/15.
 */
@NoRepositoryBean
public interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    T findOne(ID id);

    T save(T entity);

    void deleteAll();
}
