package blogpian.repositories;

import blogpian.entities.Post;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by rodolfo on 5/26/15.
 */
@CacheConfig(cacheNames = "posts")
public interface PostRepository extends MyBaseRepository<Post, Long> {

    @Cacheable
    List<Post> findByIsPublished(Boolean isPublished, Sort sort);

    @CacheEvict(allEntries = true)
    Post save(Post entity);

    @CacheEvict(allEntries = true)
    void deleteAll();
}
