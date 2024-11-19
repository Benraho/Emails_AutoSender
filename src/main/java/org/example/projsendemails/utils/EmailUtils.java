package org.example.projsendemails.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class EmailUtils {

    public static List<String> readEmailsFromFile(String filePath) throws Exception {
        // Charger le fichier depuis le dossier resources
        ClassPathResource resource = new ClassPathResource(filePath);
        try (InputStream inputStream = resource.getInputStream()) {
            // Lire le contenu du fichier et retourner les emails
            String content = new String(inputStream.readAllBytes());
            return List.of(content.split(","));
        }
    }

    public static String getResourceFilePath(String resourceFileName) throws Exception {
        // Créer un fichier temporaire à partir du fichier dans resources
        ClassPathResource resource = new ClassPathResource(resourceFileName);
        Path tempFile = Files.createTempFile("temp_", resourceFileName);
        try (InputStream inputStream = resource.getInputStream()) {
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return tempFile.toAbsolutePath().toString();
    }
}
