package com.practise.controller;

import com.practise.entity.ShipEntity;
import com.practise.service.ShipService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShipController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    ShipService shipService;

    @Autowired
    HttpSession httpSession;

    @PostMapping("/search-ship")
    public ModelAndView getShipDetails() {
        if (httpSession.getAttribute("user") != null) {
            String source = (String) httpServletRequest.getParameter("from");
            String destination = (String) httpServletRequest.getParameter("to");
            ModelAndView modelAndView = new ModelAndView("searchresult");
            List<ShipEntity> ships = shipService.getShipBySourceAndDestination(source, destination);
            modelAndView.addObject("ships", ships);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
    }

}
