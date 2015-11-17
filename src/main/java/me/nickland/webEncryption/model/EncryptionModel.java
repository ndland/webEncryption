package me.nickland.webEncryption.model;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.text.Document;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Nick on 4/7/15.
 */
public class EncryptionModel {

    public boolean encryptPdf(String src, String dest, String password) throws IOException, DocumentException {

        //Check if file is already encrypted


        try {
            PdfReader reader = new PdfReader(src);

            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
            //System.out.println("Encrypting File");
            stamper.setEncryption(password.getBytes(), password.getBytes(),
                    PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_256 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
            //System.out.println("yes");

            stamper.close();
            reader.close();
            return true;

        }
        catch(IOException e){
            return false;

        }
    }


    public boolean decryptPdf(String src, String dest, String password) throws IOException, DocumentException {

        try {
            PdfReader reader = new PdfReader(src, password.getBytes());
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
            stamper.close();
            reader.close();

            return true;
        }
        catch(IOException e){
            return false;
        }
    }


}
