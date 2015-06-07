package com.sound.server.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Francis on 2015-06-07.
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/manage")
public class AdminController {
    
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String apiPage(Model model){
        return "manage/api";
    }
    
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authManagePage(Model model){
        return "auth";
    }
}
