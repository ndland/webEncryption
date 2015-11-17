package me.nickland.webEncryption.controller;

import com.itextpdf.text.DocumentException;
import me.nickland.webEncryption.model.EncryptionModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
                               @RequestParam(value = "password") String password, Model model) throws IOException, DocumentException {
        model.addAttribute("transaction", "Encrypted PDF");
        model.addAttribute("file", file);
        model.addAttribute("password", password);

        boolean valid;
        //Create an object of the Encryption Model
        EncryptionModel eM = new EncryptionModel();

        System.out.println(file.getAbsoluteFile());

        //Send the file to be encrypted
        //          SRC: Source of file to be encyrpted
        //          DEST: Destination of new encrypted file to be saved
        //          Password: to encrypt the file with
        valid = eM.encryptPdf("FilesToBeEncrypted/" + file, "EncryptedFiles/" + file + "_Encrypted.pdf", password);
        if(valid == true)
            return "processedfile";
        else
            return "fileFailed";

    }

    @RequestMapping(value = "/decryptFile", method = RequestMethod.POST)
    public String decryptFile(@RequestParam(value = "file", required = true) File file,
                              @RequestParam(value = "password", required = true) String password, Model model)throws IOException, DocumentException {
        model.addAttribute("transaction", "Decrypted PDF");
        model.addAttribute("file", file);
        model.addAttribute("password", password);

        boolean valid;
        //Create an object of the Encryption Model
        EncryptionModel eM = new EncryptionModel();

        //Decrypt the file
        //Sends in - SRC: source of file that is encrypted,
        //          DEST: Decrypted file to be saved to
        //          Password: Password used to encrypt file
        valid = eM.decryptPdf("EncryptedFiles/" + file, "DecryptedFiles/" + file + "_Decrypted.pdf", password);

        if(valid == true)
        return "processedfile";

        else
            return "fileFailedDecrypt";
    }

}
