package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Comment;
import pl.coderslab.entities.Tweet;
import pl.coderslab.repositories.CommentRepository;
import pl.coderslab.repositories.TweetRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

 @PostMapping("/app/main/comments")
    public String postMain(@Valid Comment comment, BindingResult result, Model model,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!result.hasErrors()) {
            commentRepository.save(comment);
            response.sendRedirect(request.getContextPath() + "/app/main");
        } else {
            response.sendRedirect(request.getContextPath() + "/app/main?err=" + comment.getTweet().getId());
        }
        return null;
    }

}
