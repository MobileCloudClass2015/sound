package com.sound.server.admin;

import com.sound.server.auth.Auth;
import com.sound.server.auth.AuthSearchFilter;
import com.sound.server.auth.AuthService;
import com.sound.server.sound.Sound;
import com.sound.server.sound.SoundFilter;
import com.sound.server.sound.SoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Francis on 2015-06-07.
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping("/manage")
public class AdminController {
    
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private SoundService soundService;
    
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String apiPage(Model model){
        return "manage/api";
    }
    
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String authManagePage(Model model, @ModelAttribute AuthSearchFilter authSearchFilter){
        List<Auth> authList = authService.selectAuths(authSearchFilter);
        model.addAttribute("authList", authList);
        return "manage/auth";
    }

    @RequestMapping(value = "/auth/{id}", method = RequestMethod.GET)
    public String soundList(Model model, @PathVariable("id") String id, @ModelAttribute SoundFilter soundFilter){
        soundFilter.setId(id);
        List<Sound> soundList = soundService.selectSounds(soundFilter);
        model.addAttribute("authId", id);
        model.addAttribute("soundList", soundList);
        return "manage/sound";
    }
}
