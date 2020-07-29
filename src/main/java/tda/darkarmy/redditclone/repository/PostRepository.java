package tda.darkarmy.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.redditclone.model.Post;
import tda.darkarmy.redditclone.model.Subreddit;
import tda.darkarmy.redditclone.model.User;

import java.util.Arrays;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);

}
