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

    public void encryptPdf(String src, String dest, String password) throws IOException, DocumentException {

        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        //System.out.println("Encrypting File");
        stamper.setEncryption(password.getBytes(), password.getBytes(),
                PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
        //System.out.println("yes");

        stamper.close();
        reader.close();
    }

    public void decryptPdf(String src, String dest, String password) throws IOException, DocumentException {

        PdfReader reader = new PdfReader(src, password.getBytes());
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }


}
