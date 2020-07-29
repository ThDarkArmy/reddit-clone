package tda.darkarmy.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.redditclone.model.Subreddit;

import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    Optional<Subreddit> findByName(String subredditName);

}
