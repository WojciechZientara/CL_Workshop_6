package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entities.Tweet;
import pl.coderslab.entities.User;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT DISTINCT t FROM Tweet t LEFT JOIN FETCH t.comments c ORDER BY t.created DESC, c.created DESC")
    List<Tweet> findAllTweetsOrderByCreated();

    @Query("SELECT DISTINCT t FROM Tweet t LEFT JOIN FETCH t.comments c WHERE t.user = ?1 ORDER BY t.created DESC, c.created DESC")
    List<Tweet> findTweetsByUser(User user);

    @Query("SELECT DISTINCT t FROM Tweet t LEFT JOIN FETCH t.comments c WHERE t.user = ?1 AND t.id = ?2 ORDER BY c.created DESC")
    Tweet findTweetByUserAndTweetId(User user, Long tweetId);

}
