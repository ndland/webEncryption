package me.nickland.webEncryption.controller;

import com.evernote.auth.EvernoteAuth;
import com.evernote.auth.EvernoteService;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;
import me.nickland.webEncryption.model.EvernoteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

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

    @RequestMapping(value = "/encryptFile", method = RequestMethod.POST)
    public String processInput(@RequestParam(value = "file", required = false) File file,
                               @RequestParam(value = "password") String password, Model model) {
        model.addAttribute("transaction", "Encrypted PDF");
        model.addAttribute("file", file);
        model.addAttribute("password", password);
        return "processedfile";
    }

    @RequestMapping(value = "/decryptFile", method = RequestMethod.POST)
    public String decryptFile(@RequestParam(value = "file", required = true) File file,
                              @RequestParam(value = "password", required = true) String password, Model model) {
        model.addAttribute("transaction", "Decrypted PDF");
        model.addAttribute("file", file);
        model.addAttribute("password", password);
        return "processedfile";
    }

    @RequestMapping(value = "/evernote", method = RequestMethod.GET)
    public String testEvernoteOAuth() {
        EvernoteModel em = new EvernoteModel();
        em.connectToEvernote();
        return "redirect:/";
    }
}
