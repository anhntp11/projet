package fr.orleans.m1s1miage.group4.backend.configuration;
import java.io.FileWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

/**
 * Permet à l'exécution de générer une paire de clé Privée et Publique pour les JWT
 */
public class RsaKeyGenerator {

    public static void main(String[] args) throws Exception {
        String basePath = System.getProperty("user.dir");
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();

        String privateKey = "-----BEGIN PRIVATE KEY-----\n"
                + Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(keyPair.getPrivate().getEncoded())
                + "\n-----END PRIVATE KEY-----\n";

        String publicKey = "-----BEGIN PUBLIC KEY-----\n"
                + Base64.getMimeEncoder(64, "\n".getBytes()).encodeToString(keyPair.getPublic().getEncoded())
                + "\n-----END PUBLIC KEY-----\n";

        try (FileWriter privateWriter = new FileWriter(basePath + "/private_key.pem");
             FileWriter publicWriter = new FileWriter(basePath + "/public_key.pem")) {
            privateWriter.write(privateKey);
            publicWriter.write(publicKey);
        }

        System.out.println("Clés générées : private_key.pem et public_key.pem");
    }
}