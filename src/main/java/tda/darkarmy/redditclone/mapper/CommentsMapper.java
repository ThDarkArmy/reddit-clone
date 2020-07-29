package tda.darkarmy.redditclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tda.darkarmy.redditclone.dto.CommentsDto;
import tda.darkarmy.redditclone.model.Comment;
import tda.darkarmy.redditclone.model.Post;
import tda.darkarmy.redditclone.model.User;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    CommentsDto mapToDto(Comment comment);
}
