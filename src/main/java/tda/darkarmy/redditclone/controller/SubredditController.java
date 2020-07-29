package tda.darkarmy.redditclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.redditclone.dto.SubredditDto;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.service.SubredditService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<?> createSubreddit(@RequestBody SubredditDto subredditDto){
        return status(HttpStatus.CREATED).body(subredditService.save(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllSubreddit(){
        return status(OK).body(subredditService.getAllSubreddit());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubreddit(@PathVariable Long id) throws SpringRedditException {
        return status(OK).body(subredditService.getSubreddit(id));
    }
}
