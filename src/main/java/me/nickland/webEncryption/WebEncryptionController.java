package me.nickland.webEncryption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nland on 3/6/2015.
 */

@Controller
public class WebEncryptionController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello, From Thymeleaf!");
        return "hello";
    }
}
