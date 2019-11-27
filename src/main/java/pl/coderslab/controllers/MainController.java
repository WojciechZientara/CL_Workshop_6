package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entities.Comment;
import pl.coderslab.entities.Tweet;
import pl.coderslab.repositories.CommentRepository;
import pl.coderslab.repositories.TweetRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    TweetRepository tweetRepository;

    @GetMapping("/app/main")
    public String getMain(HttpServletRequest request, Model model) {
        List<Tweet> tweets = tweetRepository.findAllTweetsOrderByCreated();
        model.addAttribute("tweets", tweets);
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("comment", new Comment());
        if (request.getParameter("err") != null ) {
            long err = Long.parseLong(request.getParameter("err"));
            model.addAttribute("err", err);
        }
        return "app/main";
    }

    @PostMapping("/app/main")
    public String postMain(@Valid Tweet tweet, BindingResult result, Model model,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!result.hasErrors()) {
            tweetRepository.save(tweet);
            response.sendRedirect(request.getContextPath() + "/app/main");
        } else {
            List<Tweet> tweets = tweetRepository.findAllTweetsOrderByCreated();
            model.addAttribute("tweets", tweets);
            return "app/main";
        }
        return null;
    }

}
