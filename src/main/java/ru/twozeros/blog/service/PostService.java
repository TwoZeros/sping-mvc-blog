package ru.twozeros.blog.service;



import org.springframework.stereotype.Service;
import ru.twozeros.blog.exception.NotFoundException;
import ru.twozeros.blog.model.Post;
import ru.twozeros.blog.repository.PostRepository;

import java.util.List;
@Service
public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    return repository.save(post);
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

