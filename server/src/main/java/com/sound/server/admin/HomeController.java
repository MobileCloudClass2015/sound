package com.sound.server.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Francis on 2015-06-07.
 */
@Controller
public class HomeController {
    
    @RequestMapping(value = {"/", "/signIn"}, method = RequestMethod.GET)
    public String indexPage() {
        return "signIn";
    }

    @RequestMapping(value = "/noPermission", method = RequestMethod.GET)
    public String permissionPage(Model model) {
        return "noPermission";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard";
    }
}

