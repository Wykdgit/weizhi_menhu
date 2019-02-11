package com.wizz.demo.Controller;

import com.wizz.demo.model.User;
import com.wizz.demo.service.UserService;
import com.wizz.demo.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/wrong")
    public String wrong() {
        return "wrong";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)

    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult,
                          @RequestParam(value = "profile", required = false) MultipartFile photo, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        try {
            ImageUtil.validateImage(photo);
            ImageUtil.saveImage("D:\\weizhi\\images\\" + user.getName(), photo);

        } catch (Exception e) {
            bindingResult.reject(e.getMessage());
            return "signup";
        }

        if (user.getPower().equals(User.Power.ROLE_USER))
            userService.insert(user);
        return "login";
    }

    @RequestMapping(value = "/jump")
    public String jump(HttpServletRequest request) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = (User) request.getSession().getAttribute("currUser");
        if (u != null) {
            return "redirect:/home/history";
        }
        User currUser = (User) userService.getUserByName(user.getUsername());
        request.getSession().setAttribute("currUser", currUser);
        return "redirect:/home/history";
    }
}
