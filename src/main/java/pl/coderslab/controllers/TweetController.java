package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Tweet;
import pl.coderslab.repositories.TweetRepository;
import pl.coderslab.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TweetController {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/app/myTweets")
    public String getUserTweets(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        List<Tweet> tweets = tweetRepository.findTweetsByUser(userRepository.findOne(userId));
        model.addAttribute("tweets", tweets);
        return "app/myTweets";
    }

    @GetMapping("/app/myTweets/update/{tweetId}")
    public String getUpdateTweet(@PathVariable long tweetId, Model model,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        Tweet tweet = tweetRepository.findTweetByUserAndTweetId(userRepository.findOne(userId), tweetId);
        model.addAttribute("tweet", tweet);
        return "app/displayTweet";
    }

    @PostMapping("/app/myTweets/update/{tweetId}")
    public String postUpdateTweet(@PathVariable long tweetId ,@Valid Tweet tweet, BindingResult result, Model model,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!result.hasErrors()) {
            tweet.setId(tweetId);
            tweetRepository.save(tweet);
            response.sendRedirect(request.getContextPath() + "/app/myTweets");
            return null;
        }
        return "app/displayTweet";
    }


    @GetMapping("/app/myTweets/delete/{tweetId}")
    public void deleteAuthor(@PathVariable long tweetId,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        Tweet tweet = tweetRepository.findTweetByUserAndTweetId(userRepository.findOne(userId), tweetId);
        tweetRepository.delete(tweet);
        response.sendRedirect(request.getContextPath() + "/app/myTweets");
    }

}
