package ru.twozeros.blog.repository;

import org.springframework.stereotype.Repository;
import ru.twozeros.blog.exception.NotFoundException;
import ru.twozeros.blog.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> postConcurrentHashMap;
    private final AtomicLong primaryKey;

    public PostRepository() {
        this.postConcurrentHashMap = new ConcurrentHashMap<>();
        this.primaryKey = new AtomicLong();
    }

    public List<Post> all() {
        return new ArrayList<>(postConcurrentHashMap.values());
    }

    public Optional<Post> getById(long id) {
        if (postConcurrentHashMap.containsKey(id)) {
            return Optional.of(postConcurrentHashMap.get(id));
        } else {
            return Optional.empty();
        }
    }

    public Post save(Post post) {
        var id = post.getId();
        if (id == 0) {
            post.setId(primaryKey.incrementAndGet());
            postConcurrentHashMap.put(post.getId(), post);
            return post;
        } else if (!postConcurrentHashMap.containsKey(id)) {
            throw new NotFoundException("Пост с Id " + post.getId() + "не найден");
        } else {
            postConcurrentHashMap.put(id, post);
            return post;
        }
    }

    public void removeById(long id) {
      if (!postConcurrentHashMap.containsKey(id)) {
        throw new NotFoundException("Пост с Id " + id + "не существует или уже удалён");
      }
      postConcurrentHashMap.remove(id);
    }
}
