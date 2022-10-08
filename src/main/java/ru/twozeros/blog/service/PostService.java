package ru.twozeros.blog.service;



import org.springframework.stereotype.Service;
import ru.twozeros.blog.dto.PostDto;
import ru.twozeros.blog.exception.NotFoundException;
import ru.twozeros.blog.mapper.PostMapper;
import ru.twozeros.blog.model.Post;
import ru.twozeros.blog.repository.PostRepository;

import java.util.List;
@Service
public class PostService {
  private final PostRepository repository;
  private final PostMapper mapper;

  public PostService(PostRepository repository, PostMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<PostDto> all() {
    return mapper.toDtoList(repository.all()) ;
  }

  public PostDto getById(long id) {
    return mapper.toDto(repository
            .getById(id).
            orElseThrow(NotFoundException::new)) ;
  }

  public PostDto save(PostDto post) {
    return mapper.toDto(repository.save(mapper.toEntity(post)));
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

