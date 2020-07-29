package tda.darkarmy.redditclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tda.darkarmy.redditclone.dto.VoteDto;
import tda.darkarmy.redditclone.exception.PostNotFoundException;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.model.Post;
import tda.darkarmy.redditclone.model.Vote;
import tda.darkarmy.redditclone.repository.PostRepository;
import tda.darkarmy.redditclone.repository.UserRepository;
import tda.darkarmy.redditclone.repository.VoteRepository;

import java.util.Optional;

import static tda.darkarmy.redditclone.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
@Slf4j
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;


    @Transactional
    public void vote(VoteDto voteDto) throws PostNotFoundException, SpringRedditException {
        Post post = postRepository.findById(voteDto.getPostId()).orElseThrow(()-> new PostNotFoundException("Post not found with id: "+voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());

        if(voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())){
            throw new SpringRedditException("You have already "+ voteDto.getVoteType()+" voted for this post.");
        }

        if(UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount()+1);
        }else{
            post.setVoteCount(post.getVoteCount()-1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .post(post)
                .voteType(voteDto.getVoteType())
                .user(authService.getCurrentUser())
                .build();
    }

}
