package tda.darkarmy.redditclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.redditclone.dto.CommentsDto;
import tda.darkarmy.redditclone.exception.PostNotFoundException;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.service.CommentService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentsDto commentsDto) throws SpringRedditException, PostNotFoundException {
        commentService.save(commentsDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<?> getAllCommentsForPost(@PathVariable Long postId) throws PostNotFoundException {
        return status(HttpStatus.OK).body(commentService.getALlCommentsForPost(postId));
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<?> getAllCommentsForUser(@PathVariable String username) throws UsernameNotFoundException {
        return status(HttpStatus.OK).body(commentService.getALlCommentsForUser(username));
    }
}
