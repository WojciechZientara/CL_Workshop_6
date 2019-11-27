package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entities.Comment;
import pl.coderslab.entities.Tweet;
import pl.coderslab.entities.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.tweet = ?1 ORDER BY c.created DESC")
    List<Comment> findAllCommentsByTweetOrderByCreated(Tweet tweet);

    @Query(value = "SELECT * FROM comments WHERE tweet_id = ?1 ORDER BY created DESC", nativeQuery = true)
    List<Comment> findAllCommentsByTweetIdOrderByCreated(long id);

}
