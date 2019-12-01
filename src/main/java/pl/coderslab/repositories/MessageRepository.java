package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entities.Message;
import pl.coderslab.entities.Tweet;
import pl.coderslab.entities.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT DISTINCT m FROM Message m WHERE m.recipient = ?1 OR m.sender = ?1 ORDER BY m.created DESC")
    List<Message> findAllMessagesByRecipient(User user);

    @Query("SELECT m FROM Message m WHERE m.recipient = ?1 OR m.sender = ?1 AND m.id = ?2")
    Message findMessageByUserAndMessageId(User user, Long id);

}
