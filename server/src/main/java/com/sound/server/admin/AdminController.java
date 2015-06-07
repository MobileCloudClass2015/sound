package com.sound.server.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Francis on 2015-06-07.
 */
@Controller
public class AdminController {
    
    @RequestMapping(value = {"/", "/signIn"}, method = RequestMethod.GET)
    public String indexPage() {
        return "signIn";
    }

    @RequestMapping(value = "/noPermission", method = RequestMethod.GET)
    public String permissionPage(Model model) {
        return "noPermission";
    }

    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String printWelcome() {
        return "hello";
    }
}

