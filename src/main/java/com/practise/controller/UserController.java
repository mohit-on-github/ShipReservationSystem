package com.practise.controller;

import com.practise.entity.BookingEntity;
import com.practise.entity.UserEntity;
import com.practise.service.BookingService;
import com.practise.service.LoginService;
import com.practise.service.ShipService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Controller
public class UserController {


    @Autowired
    BookingService bookingService;

    @Autowired
    LoginService loginService;

    @Autowired
    ShipService shipService;

    @Autowired
    HttpSession httpSession;

    @GetMapping({"/"})
    public ModelAndView loginPage(@RequestParam(value = "credentialsError", required = false) boolean error) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("credentialsError", error);
        return modelAndView;
    }
    @GetMapping("/index")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @PostMapping("/dashboard")
    public ModelAndView loginUser(@RequestParam("userEmail") String userEmail, @RequestParam("userPass") String userPass) {
        if (loginService.validateUser(userEmail, userPass)) {
            ModelAndView modelAndView = new ModelAndView("dashboard");

            HashSet<String> sources = shipService.getAllSources();
            modelAndView.addObject("sources", sources);
            HashSet<String> destinations = shipService.getAllDestinations();
            modelAndView.addObject("destinations", destinations);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/");
            modelAndView.addObject("credentialsError", true);
            return modelAndView;
        }
    }

    @GetMapping("/home")
    public ModelAndView dashboardPage() {
        if (httpSession.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView("dashboard");
            modelAndView.addObject("booking", new BookingEntity());

            HashSet<String> sources = shipService.getAllSources();
            modelAndView.addObject("sources", sources);
            HashSet<String> destinations = shipService.getAllDestinations();
            modelAndView.addObject("destinations", destinations);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

    @GetMapping("/addUser")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam("userName") String userName,
                                     @RequestParam("userEmail") String userEmail,
                                     @RequestParam("userPass") String userPass,
                                     @RequestParam("age") int age,
                                     @RequestParam("MobNo") String mobNo) {
        if (loginService.saveUser(userName, userEmail, userPass, age, mobNo)) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("save", true);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("saveError", true);
            return modelAndView;
        }
    }

    @GetMapping("/profile")
    public ModelAndView userProfile() {
        if (httpSession.getAttribute("user") != null) {
            UserEntity user = (UserEntity) httpSession.getAttribute("user");
            ModelAndView modelAndView = new ModelAndView("profile");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

    @GetMapping("/changePage")
    public ModelAndView changePassPage(){
        if (httpSession.getAttribute("user") != null) {
            return new ModelAndView("changePass");
        }

        else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }


    @PostMapping("/changePassword")
    public ModelAndView changerUserPass(@RequestParam("currentPassword") String currentPass,
                                        @RequestParam("newPassword") String newPass) {

        if (httpSession.getAttribute("user") != null) {
            String passCheck = loginService.setNewPass(currentPass, newPass);
            if (passCheck.equals("changed")) {
                ModelAndView modelAndView = new ModelAndView("changePass");
                modelAndView.addObject("changePassSuccess", true);
                return modelAndView;
            }
            else if(passCheck.equals("sameOldNew")) {
                ModelAndView modelAndView = new ModelAndView("changePass");
                modelAndView.addObject("samePassError",true);
                return modelAndView;
            }
            else {
                ModelAndView modelAndView = new ModelAndView("changePass");
                modelAndView.addObject("notMatchedErr", true);
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    public ModelAndView logoutUser() {
        ModelAndView modelAndView = new ModelAndView("login");
       modelAndView.addObject("logoutSuccess", true);
        httpSession.invalidate();
        return modelAndView;

    }
}
