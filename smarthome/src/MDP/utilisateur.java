package MDP;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class utilisateur {
    private int id;
    private String nomUtilisateur;
    private String motDePasseHash; // Nous renommons ceci pour clarifier que c'est un hachage
    private String adresseEmail;
    private int niveauAccès;

    public utilisateur(int id, String nomUtilisateur, String motDePasse, String adresseEmail, int niveauAccès) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasseHash = hashPassword(motDePasse); // Nous hachons le mot de passe avant de le stocker
        this.adresseEmail = adresseEmail;
        this.niveauAccès = niveauAccès;
    }

    // Une fonction simple pour hacher le mot de passe (à titre d'exemple, pas recommandé pour une utilisation réelle!)
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Vérifier si le mot de passe donné est correct
    public boolean isPasswordCorrect(String inputPassword) {
        return hashPassword(inputPassword).equals(motDePasseHash);
    }

    // Mettre à jour le mot de passe de l'utilisateur
    public void changePassword(String newPassword) {
        this.motDePasseHash = hashPassword(newPassword);
    }

    // Le reste de vos getters et setters ...

    @Override
    public String toString() {
        return "Utilisateur ID: " + id + ", Nom Utilisateur: " + nomUtilisateur + ", Adresse Email: " + adresseEmail + ", Niveau d'Accès: " + niveauAccès;
    }
}
