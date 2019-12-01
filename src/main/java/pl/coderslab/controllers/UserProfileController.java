package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entities.Tweet;
import pl.coderslab.entities.User;
import pl.coderslab.repositories.TweetRepository;
import pl.coderslab.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserProfileController {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/app/userProfile")
    public String getOwnProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        User user = userRepository.findOne(userId);
        model.addAttribute("user", user);
        return "app/editProfile";
    }

    @PostMapping("/app/userProfile")
    public String postOwnProfile(@Valid User user, BindingResult result, Model model,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (result.hasErrors()) {
            return "app/editProfile";
        } else {
            userRepository.save(user);
            HttpSession session = request.getSession();
            session.setAttribute("userName",user.getFirstName() + " " + user.getLastName());
            response.sendRedirect(request.getContextPath() + "/app/main");
        }
        return null;
    }

    @GetMapping("/app/userProfile/{userId}")
    public String getUserProfile(@PathVariable long userId, HttpServletRequest request, Model model) {
        List<Tweet> tweets = tweetRepository.findTweetsByUser(userRepository.findOne(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("tweets", tweets);
        return "app/userProfile";
    }

}
