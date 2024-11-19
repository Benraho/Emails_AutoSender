package org.example.projsendemails.controller;

import org.example.projsendemails.service.EmailService;
import org.example.projsendemails.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmails")
    public String sendEmails() {
        try {
            // Charger la liste des emails depuis le fichier dans resources
            List<String> emails = EmailUtils.readEmailsFromFile("emails.txt");

            // Récupérer le chemin du CV depuis resources
            String cvPath = EmailUtils.getResourceFilePath("CV_Samira_Benraho.pdf");

            // Envoyer les emails un par un
            for (String email : emails) {
                emailService.sendEmailWithAttachment(
                        email.trim(),
                        "Candidature pour le Stage PFE – Développement Informatique",
                        "Bonjour,\n\n" +
                                "Je suis Samira Benraho, étudiante en dernière année de Génie Informatique à l'ENSA de Marrakech. " +
                                "Passionnée par le développement web, j'ai développé des compétences solides en Java, Spring Boot, Angular et React, " +
                                "que j’ai appliquées dans des projets académiques et pratiques, démontrant ma capacité à concevoir des solutions efficaces.\n\n" +
                                "Je serais honorée de rejoindre vos équipes et de contribuer activement à vos projets ambitieux. " +
                                "Vous trouverez mon CV en pièce jointe pour plus d'informations sur mon parcours et mes réalisations.\n\n" +
                                "Cordialement,\n" +
                                "Samira Benraho\n" +
                                "benrahosamira2@gmail.com\n" +
                                "+212 701 661 521",
                        cvPath
                );

                // Pause de 2 secondes entre les envois
                Thread.sleep(2000);
            }

            return "Emails envoyés avec succès !";

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'envoi des emails : " + e.getMessage();
        }
    }
}
