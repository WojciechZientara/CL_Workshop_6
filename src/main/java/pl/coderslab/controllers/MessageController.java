package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Message;
import pl.coderslab.entities.Tweet;
import pl.coderslab.repositories.MessageRepository;
import pl.coderslab.repositories.TweetRepository;
import pl.coderslab.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/app/messages")
    public String getMessages(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        List<Message> messages = messageRepository.findAllMessagesByRecipient(userRepository.findOne(userId));
        model.addAttribute("messages", messages);
        return "app/messages";
    }

    @GetMapping("/app/messages/{messageId}")
    public String getMessages(@PathVariable long messageId, HttpServletRequest request, Model model) {
        Message message = messageRepository.findOne(messageId);
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        if (message.getSender().getId() != userId) {
            message.setRead(true);
            messageRepository.save(message);
        }
        model.addAttribute("message", message);
        return "app/showMessage";
    }

    @GetMapping("/app/messages/send/{recipientId}")
    public String getSendMessage(@PathVariable long recipientId, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        long senderId = (long) session.getAttribute("id");
        Message message = new Message();
        message.setSender(userRepository.findOne(senderId));
        message.setRecipient(userRepository.findOne(recipientId));
        model.addAttribute("message", message);
        return "app/sendMessage";
    }

    @PostMapping("/app/messages/send/{recipientId}")
    public String postSendMessage(@Valid Message message, BindingResult result, Model model,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (result.hasErrors()) {
            return "app/sendMessage";
        } else {
            messageRepository.save(message);
            response.sendRedirect(request.getContextPath() + "/app/messages");
        }
        return null;
    }

    @GetMapping("/app/messages/delete/{messageId}")
    public void deleteMessage(@PathVariable long messageId,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        Message message = messageRepository.findMessageByUserAndMessageId(userRepository.findOne(userId), messageId);
        messageRepository.delete(message);
        response.sendRedirect(request.getContextPath() + "/app/messages");
    }

}
