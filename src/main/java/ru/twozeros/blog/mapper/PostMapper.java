package ru.twozeros.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.twozeros.blog.dto.PostDto;
import ru.twozeros.blog.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class PostMapper implements IMapper<Post,PostDto>
{
    @Autowired
    private ModelMapper mapper;

    @Override
    public Post toEntity(PostDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Post.class);
    }

    public List<PostDto> toDtoList(List<Post> entityList) {

        return Objects.isNull(entityList) ? null :entityList.stream()
                .map( i -> mapper.map(i, PostDto.class))
                .toList();
    }
    @Override
    public PostDto toDto(Post entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, PostDto.class);
    }
}
