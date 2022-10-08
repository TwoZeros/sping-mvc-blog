package ru.twozeros.blog.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import ru.twozeros.blog.dto.PostDto;
import ru.twozeros.blog.model.Post;
import ru.twozeros.blog.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<PostDto> all() {
    return service.all();
  }

  @GetMapping("/{id}")
  public PostDto getById(@PathVariable long id) {
    return service.getById(id);
  }

  @PostMapping
  public PostDto save(@RequestBody PostDto post) {
    return service.save(post);
  }

  @DeleteMapping("/{id}")
  public void removeById(@PathVariable long id) {
    service.removeById(id);
  }
}
