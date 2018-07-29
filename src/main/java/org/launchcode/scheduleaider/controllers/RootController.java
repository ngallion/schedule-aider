package org.launchcode.scheduleaider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class RootController {

    @RequestMapping(value = "")
    public String index(){
        return "redirect:home";
    }

}
