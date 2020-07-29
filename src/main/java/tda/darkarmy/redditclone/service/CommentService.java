package tda.darkarmy.redditclone.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tda.darkarmy.redditclone.dto.CommentsDto;
import tda.darkarmy.redditclone.exception.PostNotFoundException;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.mapper.CommentsMapper;
import tda.darkarmy.redditclone.model.*;
import tda.darkarmy.redditclone.repository.CommentRepository;
import tda.darkarmy.redditclone.repository.PostRepository;
import tda.darkarmy.redditclone.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentsMapper commentsMapper;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;


    public void save(CommentsDto commentsDto) throws PostNotFoundException, SpringRedditException {
        Post post = postRepository.findById(commentsDto.getPostId()).orElseThrow(()->
                new PostNotFoundException("Post not found with id: "+commentsDto.getPostId()));
        Comment comment = commentsMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(commentsDto.getUsername()+" posted a comment on your post. ",post.getUrl());
        sendCommentNotification(message, post.getUser(), post);

    }

    private void sendCommentNotification(String message, User user, Post post) throws SpringRedditException {
        mailService.sendMail(new NotificationEmail(user.getUsername()+" someone commented on your post. ",user.getEmail(), message, post.getUrl()));

    }

    public List<CommentsDto> getALlCommentsForPost(Long postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new PostNotFoundException("Post not found with id: "+postId));
        return commentRepository.findByPost(post).stream().map(commentsMapper::mapToDto).collect(Collectors.toList());
    }

    public List<CommentsDto> getALlCommentsForUser(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User with username:"+ username+ "not found!"));
        return commentRepository.findAllByUser(user).stream().map(commentsMapper::mapToDto).collect(Collectors.toList());
    }
}
