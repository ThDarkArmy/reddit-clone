package tda.darkarmy.redditclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tda.darkarmy.redditclone.dto.VoteDto;
import tda.darkarmy.redditclone.exception.PostNotFoundException;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.service.VoteService;

@RestController
@RequestMapping("/api/vote")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<?> vote(@RequestBody VoteDto voteDto) throws SpringRedditException, PostNotFoundException {
        voteService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
