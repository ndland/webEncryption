package me.nickland.webEncryption;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nland on 3/6/2015.
 */

@RestController
public class WebEncryptionController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to what will become a WebEncryption utility!";
    }

    @RequestMapping("/newurl")
    public String newUrl() {
        return "This is a new page, possibly a returned file?";
    }
}
