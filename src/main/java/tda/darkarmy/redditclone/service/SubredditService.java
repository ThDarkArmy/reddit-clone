package tda.darkarmy.redditclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tda.darkarmy.redditclone.dto.SubredditDto;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.mapper.SubredditMapper;
import tda.darkarmy.redditclone.model.Subreddit;
import tda.darkarmy.redditclone.repository.SubredditRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;


    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditMapper.mapDtoToSubreddit(subredditDto);
        subredditRepository.save(subreddit);
        return subredditDto;
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription()).build();
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAllSubreddit() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .description(subreddit.getDescription())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }


    public SubredditDto getSubreddit(Long id) throws SpringRedditException {
        Subreddit subreddit =  subredditRepository.findById(id).orElseThrow(()-> new SpringRedditException("No subreddit found with id: "+id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
