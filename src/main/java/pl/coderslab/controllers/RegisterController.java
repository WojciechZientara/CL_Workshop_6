package pl.coderslab.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entities.User;
import pl.coderslab.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String getIndex(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postIndex(@Valid User user, BindingResult result, Model model,
                            HttpServletRequest request, HttpServletResponse response ) throws IOException {

        try{
            if (result.hasErrors()) {
                return "register";
            } else {
                userRepository.save(user);
                response.sendRedirect(request.getContextPath() + "/");
            }
        } catch (Exception e) {
            model.addAttribute("userExists", true);
            return "register";
        }
        return null;
    }

}
