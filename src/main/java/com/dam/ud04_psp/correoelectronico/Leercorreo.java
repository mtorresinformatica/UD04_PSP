/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.ud04_psp.correoelectronico;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Store;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author DAM
 */
public class Leercorreo {

    public static void main(String[] args) throws NoSuchProviderException,
            MessagingException, IOException {

        final Properties prop = new Properties();
        prop.put("mail.imap.username", "manueltorres.informatica@gmail.com");
        prop.put("mail.imap.password", "tbcurryhmqjqulju");
        prop.put("mail.imap.host", "imap.gmail.com");
        prop.put("mail.imap.port", "993");
        prop.put("mail.imap.ssl.enable", "true");
        prop.put("mail.imap.auth", "true");
       // prop.put("mail.debug", "true");

        // Create the Session with the user credentials
        Session mailSession = Session.getInstance(prop, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(prop.getProperty("mail.imap.username"),
                        prop.getProperty("mail.imap.password"));
            }
        });

        // Get the Store object and connect to the current host using the specified username and password.
        Store store = mailSession.getStore("imap");
        store.connect(prop.getProperty("mail.imap.host"),
                prop.getProperty("mail.imap.username"),
                prop.getProperty("mail.imap.password"));

        // Get the folder and open it
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        // Get the messages
        Message[] messages = folder.getMessages();

        // Process the messages
        for (int i = 0; i < messages.length; i++) {
            Message message = messages[i];
            System.out.println("Message " + (i + 1));
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Subject: " + message.getSubject());
            System.out.println("Sent Date: " + message.getSentDate());
            System.out.println("Text: " + message.getContent().toString());
        }

        // Close the folder and store objects
        folder.close(false);
        store.close();

    }

}
